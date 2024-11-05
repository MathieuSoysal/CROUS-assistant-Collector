use log::{debug, error, info};

use serde_json::{json, Value};
use std::error::Error;

const URL_CROUS: &str = "https://trouverunlogement.lescrous.fr/api/fr/search/37";

pub async fn get_logements_from_crous() -> Result<serde_json::Value, Box<dyn Error>> {
    let response_json = request_to_crous().await?;
    let items_node = extract_items_node(&response_json)?;
    Ok(items_node.clone())
}

async fn build_request_body() -> serde_json::Value {
    info!("Building JSON request body.");
    serde_json::json!({
        "idTool": 37,
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

async fn request_to_crous() -> Result<serde_json::Value, Box<dyn Error>> {
    let json_body = build_request_body().await;
    info!("Executing HTTP POST request to {}", URL_CROUS);
    let client = reqwest::Client::new();
    let response = client.post(URL_CROUS).json(&json_body).send().await?;
    if response.status().is_success() {
        debug!("HTTP response status: {}", response.status());
        let json_response: serde_json::Value = response.json().await?;
        Ok(json_response)
    } else {
        let message = format!(
            "HTTP request failed with status code: {}",
            response.status()
        );
        error!("{}", message);
        if response.status().is_server_error() {
            return Ok(json!({}));
        }
        Err(message.into())
    }
}

fn extract_items_node(response_json: &Value) -> Result<&Value, Box<dyn Error>> {
    info!("Extracting items.");
    if response_json.is_null() {
        return Ok(&Value::Null);
    }
    let items_node = response_json
        .pointer("/results/items")
        .ok_or("Items node not found in the response")?;
    if items_node.as_array().unwrap().is_empty() {
        error!("Items node is empty.");
        return Err("Items node is empty".into());
    }
    debug!("Extracted items node.");
    Ok(items_node)
}
