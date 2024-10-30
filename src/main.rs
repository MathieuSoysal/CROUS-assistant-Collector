use log::{debug, error, info, warn};
use mongodb::{
    bson::{doc, Bson, Document},
    options::{ClientOptions, ServerApi, ServerApiVersion},
    Client, Collection,
};
use serde_json::Value;
use std::env;
use std::error::Error;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    env_logger::init();
    info!("Starting the MongoDB JSON Importer.");
    let args: Vec<String> = env::args().collect();
    let mongodb_uri = parse_arguments(&args)?;
    let url = "https://trouverunlogement.lescrous.fr/api/fr/search/36";
    let json_request_body = build_request_body();
    let response_json = execute_http_request(url, &json_request_body).await?;
    let items_node = extract_items_node(&response_json)?;
    let client = connect_to_mongodb(&mongodb_uri).await?;
    let ids = insert_logements_into_mongodb(
        &client.database("CROUS").collection::<Document>("logements"),
        items_node,
    )
    .await?;
    insert_ids_with_timestamp_into_MongoDB(
        &client.database("CROUS").collection::<Document>("available"),
        ids,
    )
    .await?;
    info!("Data imported successfully.");
    Ok(())
}

fn parse_arguments(args: &[String]) -> Result<String, Box<dyn Error>> {
    if args.len() != 2 {
        error!("Invalid number of arguments.");
        eprintln!("Usage: {} <mongodb_uri>", args[0]);
        std::process::exit(1);
    }
    let mongodb_uri = args[1].clone();
    debug!("Parsed arguments: URI={}", mongodb_uri);
    Ok(mongodb_uri)
}

fn build_request_body() -> serde_json::Value {
    info!("Building JSON request body.");
    serde_json::json!({
        "idTool": 36,
        "need_aggregation": true,
        "page": 1,
        "pageSize": 2500,
        "sector": null,
        "occupationModes": [],
        "location": [
            { "lon": -9.9079, "lat": 51.7087 },
            { "lon": 14.3224, "lat": 40.5721 }
        ],
        "residence": null,
        "precision": 3,
        "equipment": [],
        "adaptedPmr": false,
        "toolMechanism": "residual"
    })
}

async fn execute_http_request(
    url: &str,
    json_body: &serde_json::Value,
) -> Result<serde_json::Value, Box<dyn Error>> {
    info!("Executing HTTP POST request to {}", url);
    let client = reqwest::Client::new();
    let response = client.post(url).json(json_body).send().await?;
    if response.status().is_success() {
        debug!("HTTP response status: {}", response.status());
        let response_json: serde_json::Value = response.json().await?;
        Ok(response_json)
    } else {
        error!(
            "HTTP request failed with status code: {}",
            response.status()
        );
        Err(format!(
            "HTTP request failed with status code: {}",
            response.status()
        )
        .into())
    }
}

fn extract_items_node(response_json: &serde_json::Value) -> Result<&Value, Box<dyn Error>> {
    info!("Extracting items.");
    let items_node = response_json
        .pointer("/results/items")
        .ok_or("Items node not found in the response")?;
    debug!("Extracted items node.");
    Ok(items_node)
}

async fn connect_to_mongodb(mongodb_uri: &str) -> Result<Client, Box<dyn Error>> {
    info!("Connecting to MongoDB at {}", mongodb_uri);
    let mut client_options = ClientOptions::parse(mongodb_uri).await?;
    client_options.app_name = Some("CROUS analytics".to_string());
    let server_api = ServerApi::builder().version(ServerApiVersion::V1).build();
    client_options.server_api = Some(server_api);
    let client = Client::with_options(client_options)?;
    client
        .database("CROUS")
        .run_command(doc! { "ping": 1 })
        .await
        .map_err(|e| {
            error!("Failed to connect to MongoDB: {}", e);
            e
        })?;
    info!("Successfully connected to MongoDB.");
    Ok(client)
}

async fn insert_logements_into_mongodb(
    collection: &Collection<Document>,
    items_node: &serde_json::Value,
) -> Result<Vec<Bson>, Box<dyn Error>> {
    let mut ids = Vec::new();
    if let Some(items_array) = items_node.as_array() {
        info!("Inserting {} items into MongoDB.", items_array.len());
        for item_value in items_array {
            let bson_value = mongodb::bson::to_bson(&item_value)?;
            if let Bson::Document(mut doc) = bson_value {
                if let Some(id_value) = doc.get("id").cloned() {
                    doc.insert("_id", id_value.clone());
                    ids.push(id_value.clone());
                    let filter = doc! { "_id": id_value.clone() };
                    collection.replace_one(filter, doc).await?;
                    debug!("Inserted/Updated document with _id: {:?}", id_value);
                } else {
                    warn!("Item missing 'id' field: {:?}", doc);
                }
            } else {
                warn!("Expected a document but got a different BSON type.");
            }
        }
        Ok(ids)
    } else {
        error!("Items node is not an array.");
        Err("Items node is not an array".into())
    }
}

async fn insert_ids_with_timestamp_into_MongoDB(
    collection: &Collection<Document>,
    ids: Vec<Bson>,
) -> Result<(), Box<dyn Error>> {
    let doc = doc! {
        "timestamp": mongodb::bson::DateTime::now(),
        "ids": ids,
    };
    collection.insert_one(doc).await?;
    info!("Inserted ids into timestamp collection.");
    Ok(())
}
