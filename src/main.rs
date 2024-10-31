use log::{debug, error, info};

use std::env;
use std::error::Error;
pub mod crous_mongodb;
pub mod requestor;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    env_logger::init();
    info!("Starting the MongoDB JSON Importer.");
    let mongodb_uri = get_mongodb_uri()?;
    let logements = requestor::get_logements_from_crous().await?;
    let client = crous_mongodb::connect_to_mongodb(&mongodb_uri).await?;
    let ids = crous_mongodb::insert_logements_into_mongodb(&client, &logements).await?;
    crous_mongodb::insert_ids_with_timestamp_into_mongodb(&client, ids).await?;
    info!("Data imported successfully.");
    Ok(())
}

fn get_mongodb_uri() -> Result<String, Box<dyn Error>> {
    match env::var("MONGODB_URI") {
        Ok(val) => {
            debug!("Retrieved MONGODB_URI from environment.");
            Ok(val)
        }
        Err(e) => {
            error!("MONGODB_URI environment variable not set: {}", e);
            Err("MONGODB_URI environment variable not set".into())
        }
    }
}
