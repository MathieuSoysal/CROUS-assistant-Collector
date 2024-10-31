use log::{error, info, warn};
use std::error::Error;

use mongodb::{
    bson::{doc, Bson, Document},
    options::ClientOptions,
    Client,
};

pub async fn connect_to_mongodb(mongodb_uri: &str) -> Result<Client, Box<dyn Error>> {
    info!("Connecting to MongoDB");
    let client = Client::with_options(ClientOptions::parse(mongodb_uri).await?)?;
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

pub async fn insert_logements_into_mongodb(
    client: &Client,
    items_node: &serde_json::Value,
) -> Result<Vec<Bson>, Box<dyn Error>> {
    let collection = client.database("CROUS").collection::<Document>("logements");
    let mut insertions = Vec::new();
    if let Some(items_array) = items_node.as_array() {
        info!("Inserting {} items into MongoDB.", items_array.len());
        for logement in items_array {
            insertions.push(insert_logement(logement, &collection));
        }
        Ok(futures::future::join_all(insertions).await)
    } else {
        error!("Items node is not an array.");
        Err("Items node is not an array".into())
    }
}

async fn insert_logement(
    item_value: &serde_json::Value,
    collection: &mongodb::Collection<Document>,
) -> Bson {
    let bson_value = mongodb::bson::to_bson(&item_value).unwrap();
    if let Bson::Document(mut doc) = bson_value {
        if let Some(id_value) = doc.get("id").cloned() {
            doc.insert("_id", id_value.clone());
            let filter = doc! { "_id": id_value.clone() };
            collection
                .replace_one(filter, doc)
                .upsert(true)
                .await
                .unwrap();
            info!("Inserted/Updated document with _id: {:?}", id_value);
            return id_value;
        } else {
            warn!("Item missing 'id' field: {:?}", doc);
        }
    } else {
        warn!("Expected a document but got a different BSON type.");
    }
    Bson::Null
}

pub async fn insert_ids_with_timestamp_into_mongodb(
    client: &Client,
    ids: Vec<Bson>,
) -> Result<(), Box<dyn Error>> {
    let collection = client.database("CROUS").collection::<Document>("available");
    let doc = doc! {
        "timestamp": mongodb::bson::DateTime::now(),
        "ids": ids,
    };
    collection.insert_one(doc).await?;
    info!("Inserted ids into timestamp collection.");
    Ok(())
}
