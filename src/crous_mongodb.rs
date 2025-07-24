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

#[cfg(test)]
mod tests {
    use super::*;
    use serde_json::json;
    use std::collections::HashMap;
    use std::sync::{Arc, Mutex};

    // Mock MongoDB collection for testing
    #[derive(Clone)]
    struct MockCollection {
        pub documents: Arc<Mutex<HashMap<String, Document>>>,
        pub operations: Arc<Mutex<Vec<String>>>,
    }

    impl MockCollection {
        fn new() -> Self {
            Self {
                documents: Arc::new(Mutex::new(HashMap::new())),
                operations: Arc::new(Mutex::new(Vec::new())),
            }
        }

        fn insert_document(&self, id: &str, doc: Document) {
            self.documents.lock().unwrap().insert(id.to_string(), doc);
            self.operations.lock().unwrap().push(format!("insert: {}", id));
        }

        fn replace_document(&self, id: &str, doc: Document) {
            self.documents.lock().unwrap().insert(id.to_string(), doc);
            self.operations.lock().unwrap().push(format!("replace: {}", id));
        }

        fn get_document(&self, id: &str) -> Option<Document> {
            self.documents.lock().unwrap().get(id).cloned()
        }

        fn get_operations(&self) -> Vec<String> {
            self.operations.lock().unwrap().clone()
        }

        fn document_count(&self) -> usize {
            self.documents.lock().unwrap().len()
        }
    }

    // Mock MongoDB functions for testing - simulates the behavior without actual DB calls
    async fn mock_insert_logement(
        item_value: &serde_json::Value,
        mock_collection: &MockCollection,
    ) -> Bson {
        let bson_value = mongodb::bson::to_bson(&item_value).unwrap();
        if let Bson::Document(mut doc) = bson_value {
            if let Some(id_value) = doc.get("id").cloned() {
                doc.insert("_id", id_value.clone());
                
                // Simulate upsert operation
                if let Bson::String(id_str) = &id_value {
                    if mock_collection.get_document(id_str).is_some() {
                        mock_collection.replace_document(id_str, doc);
                    } else {
                        mock_collection.insert_document(id_str, doc);
                    }
                }
                
                return id_value;
            }
        }
        Bson::Null
    }

    async fn mock_insert_logements_into_mongodb(
        mock_collection: &MockCollection,
        items_node: &serde_json::Value,
    ) -> Result<Vec<Bson>, Box<dyn Error>> {
        let mut insertions = Vec::new();
        if let Some(items_array) = items_node.as_array() {
            for logement in items_array {
                insertions.push(mock_insert_logement(logement, mock_collection).await);
            }
            Ok(insertions)
        } else {
            Err("Items node is not an array".into())
        }
    }

    #[tokio::test]
    async fn test_connect_to_mongodb_invalid_uri() {
        let result = connect_to_mongodb("invalid-uri").await;
        assert!(result.is_err());
    }

    #[tokio::test]
    async fn test_connect_to_mongodb_invalid_format() {
        let result = connect_to_mongodb("not-a-mongodb-uri").await;
        assert!(result.is_err());
    }

    #[tokio::test]
    async fn test_mock_insert_single_logement_with_id() {
        let mock_collection = MockCollection::new();
        let test_data = json!({
            "id": "test-id-1",
            "name": "Test Logement",
            "price": 300
        });

        let result = mock_insert_logement(&test_data, &mock_collection).await;
        
        assert_eq!(result, Bson::String("test-id-1".to_string()));
        assert_eq!(mock_collection.document_count(), 1);
        
        let operations = mock_collection.get_operations();
        assert_eq!(operations.len(), 1);
        assert_eq!(operations[0], "insert: test-id-1");
        
        // Verify document was stored correctly
        let stored_doc = mock_collection.get_document("test-id-1").unwrap();
        assert_eq!(stored_doc.get_str("name").unwrap(), "Test Logement");
        assert_eq!(stored_doc.get_i64("price").unwrap(), 300);
        assert_eq!(stored_doc.get_str("_id").unwrap(), "test-id-1");
    }

    #[tokio::test]
    async fn test_mock_insert_logement_without_id() {
        let mock_collection = MockCollection::new();
        let test_data = json!({
            "name": "Test Logement Without ID",
            "price": 250
        });

        let result = mock_insert_logement(&test_data, &mock_collection).await;
        
        assert_eq!(result, Bson::Null);
        assert_eq!(mock_collection.document_count(), 0);
        
        let operations = mock_collection.get_operations();
        assert_eq!(operations.len(), 0);
    }

    #[tokio::test]
    async fn test_mock_insert_logement_upsert_behavior() {
        let mock_collection = MockCollection::new();
        let test_data1 = json!({
            "id": "test-id-1",
            "name": "Original Logement",
            "price": 300
        });
        let test_data2 = json!({
            "id": "test-id-1",
            "name": "Updated Logement",
            "price": 350
        });

        // First insertion
        let result1 = mock_insert_logement(&test_data1, &mock_collection).await;
        assert_eq!(result1, Bson::String("test-id-1".to_string()));
        assert_eq!(mock_collection.document_count(), 1);

        // Second insertion (should replace)
        let result2 = mock_insert_logement(&test_data2, &mock_collection).await;
        assert_eq!(result2, Bson::String("test-id-1".to_string()));
        assert_eq!(mock_collection.document_count(), 1); // Still 1 document

        let operations = mock_collection.get_operations();
        assert_eq!(operations.len(), 2);
        assert_eq!(operations[0], "insert: test-id-1");
        assert_eq!(operations[1], "replace: test-id-1");

        // Verify document was updated
        let stored_doc = mock_collection.get_document("test-id-1").unwrap();
        assert_eq!(stored_doc.get_str("name").unwrap(), "Updated Logement");
        assert_eq!(stored_doc.get_i64("price").unwrap(), 350);
    }

    #[tokio::test]
    async fn test_mock_insert_multiple_logements() {
        let mock_collection = MockCollection::new();
        let test_data = json!([
            {
                "id": "test-id-1",
                "name": "Test Logement 1",
                "price": 300
            },
            {
                "id": "test-id-2",
                "name": "Test Logement 2",
                "price": 400
            },
            {
                "name": "Logement Without ID",
                "price": 250
            }
        ]);

        let result = mock_insert_logements_into_mongodb(&mock_collection, &test_data).await;
        
        assert!(result.is_ok());
        let ids = result.unwrap();
        assert_eq!(ids.len(), 3);
        assert_eq!(ids[0], Bson::String("test-id-1".to_string()));
        assert_eq!(ids[1], Bson::String("test-id-2".to_string()));
        assert_eq!(ids[2], Bson::Null); // No ID for third item

        // Only 2 documents should be stored (third one has no ID)
        assert_eq!(mock_collection.document_count(), 2);
        
        let operations = mock_collection.get_operations();
        assert_eq!(operations.len(), 2);
        assert!(operations.contains(&"insert: test-id-1".to_string()));
        assert!(operations.contains(&"insert: test-id-2".to_string()));
    }

    #[tokio::test]
    async fn test_mock_insert_logements_invalid_data() {
        let mock_collection = MockCollection::new();
        let non_array_data = json!("not-an-array");

        let result = mock_insert_logements_into_mongodb(&mock_collection, &non_array_data).await;
        
        assert!(result.is_err());
        assert_eq!(result.unwrap_err().to_string(), "Items node is not an array");
        assert_eq!(mock_collection.document_count(), 0);
    }

    #[tokio::test]
    async fn test_mock_insert_empty_array() {
        let mock_collection = MockCollection::new();
        let empty_data = json!([]);

        let result = mock_insert_logements_into_mongodb(&mock_collection, &empty_data).await;
        
        assert!(result.is_ok());
        let ids = result.unwrap();
        assert_eq!(ids.len(), 0);
        assert_eq!(mock_collection.document_count(), 0);
    }

    #[tokio::test]
    async fn test_mock_complex_logement_data() {
        let mock_collection = MockCollection::new();
        let complex_data = json!({
            "id": "complex-id-1",
            "name": "Complex Logement",
            "price": 450,
            "location": {
                "city": "Paris",
                "district": "15ème",
                "coordinates": [2.3522, 48.8566]
            },
            "amenities": ["wifi", "kitchen", "bathroom"],
            "available": true,
            "metadata": {
                "last_updated": "2024-01-15",
                "views": 150
            }
        });

        let result = mock_insert_logement(&complex_data, &mock_collection).await;
        
        assert_eq!(result, Bson::String("complex-id-1".to_string()));
        assert_eq!(mock_collection.document_count(), 1);
        
        let stored_doc = mock_collection.get_document("complex-id-1").unwrap();
        assert_eq!(stored_doc.get_str("name").unwrap(), "Complex Logement");
        assert!(stored_doc.get_document("location").is_ok());
        assert!(stored_doc.get_array("amenities").is_ok());
        assert!(stored_doc.get_document("metadata").is_ok());
    }

    #[tokio::test]
    async fn test_mock_concurrent_operations() {
        let mock_collection = MockCollection::new();
        let test_data1 = json!({
            "id": "concurrent-1",
            "name": "Concurrent Test 1",
            "price": 300
        });
        let test_data2 = json!({
            "id": "concurrent-2",
            "name": "Concurrent Test 2",
            "price": 400
        });

        // Simulate concurrent operations
        let (result1, result2) = tokio::join!(
            mock_insert_logement(&test_data1, &mock_collection),
            mock_insert_logement(&test_data2, &mock_collection)
        );

        assert_eq!(result1, Bson::String("concurrent-1".to_string()));
        assert_eq!(result2, Bson::String("concurrent-2".to_string()));
        assert_eq!(mock_collection.document_count(), 2);
        
        let operations = mock_collection.get_operations();
        assert_eq!(operations.len(), 2);
        // Order might vary due to concurrency, so check both exist
        assert!(operations.contains(&"insert: concurrent-1".to_string()));
        assert!(operations.contains(&"insert: concurrent-2".to_string()));
    }

    #[tokio::test]
    async fn test_mock_timestamp_collection_simulation() {
        // Mock the timestamp collection behavior
        let mock_timestamp_collection = MockCollection::new();
        
        let test_ids = vec![
            Bson::String("id1".to_string()),
            Bson::String("id2".to_string()),
            Bson::String("id3".to_string()),
        ];

        let timestamp_doc = doc! {
            "timestamp": mongodb::bson::DateTime::now(),
            "ids": test_ids.clone(),
        };

        // Simulate inserting the timestamp document
        mock_timestamp_collection.insert_document("timestamp_entry_1", timestamp_doc.clone());
        
        assert_eq!(mock_timestamp_collection.document_count(), 1);
        
        let stored_doc = mock_timestamp_collection.get_document("timestamp_entry_1").unwrap();
        assert!(stored_doc.contains_key("timestamp"));
        assert!(stored_doc.contains_key("ids"));
        
        let ids_in_doc = stored_doc.get_array("ids").unwrap();
        assert_eq!(ids_in_doc.len(), 3);
    }

    #[tokio::test]
    async fn test_mock_error_handling_scenarios() {
        let mock_collection = MockCollection::new();
        
        // Test with null JSON
        let null_data = json!(null);
        let result = mock_insert_logement(&null_data, &mock_collection).await;
        assert_eq!(result, Bson::Null);
        
        // Test with non-object JSON
        let array_data = json!([1, 2, 3]);
        let result2 = mock_insert_logement(&array_data, &mock_collection).await;
        assert_eq!(result2, Bson::Null);
        
        // Test with object missing id
        let no_id_data = json!({"name": "No ID", "price": 100});
        let result3 = mock_insert_logement(&no_id_data, &mock_collection).await;
        assert_eq!(result3, Bson::Null);
        
        assert_eq!(mock_collection.document_count(), 0);
    }

    // Original data validation tests (preserved)
    #[test]
    fn test_insert_logements_data_validation() {
        // Test with non-array JSON
        let non_array_data = json!("not-an-array");
        assert!(!non_array_data.is_array());

        // Test with valid array
        let valid_array = json!([
            {
                "id": "test-id-1",
                "name": "Test Logement 1",
                "price": 300
            }
        ]);
        assert!(valid_array.is_array());
        assert_eq!(valid_array.as_array().unwrap().len(), 1);

        // Test with empty array
        let empty_array = json!([]);
        assert!(empty_array.is_array());
        assert_eq!(empty_array.as_array().unwrap().len(), 0);
    }

    #[test]
    fn test_logement_data_structure() {
        let test_data = json!({
            "id": "test-id-1",
            "name": "Test Logement",
            "price": 300,
            "location": "Paris"
        });

        // Test BSON conversion
        let bson_value = mongodb::bson::to_bson(&test_data).unwrap();
        if let Bson::Document(doc) = bson_value {
            assert!(doc.contains_key("id"));
            assert!(doc.contains_key("name"));
            assert!(doc.contains_key("price"));
            assert_eq!(doc.get_str("id").unwrap(), "test-id-1");
            assert_eq!(doc.get_str("name").unwrap(), "Test Logement");
            // Price is stored as i64 in BSON, not i32
            assert_eq!(doc.get_i64("price").unwrap(), 300i64);
        } else {
            panic!("Expected BSON Document");
        }
    }

    #[test]
    fn test_logement_missing_id() {
        let test_data = json!({
            "name": "Test Logement Without ID",
            "price": 300
        });

        let bson_value = mongodb::bson::to_bson(&test_data).unwrap();
        if let Bson::Document(doc) = bson_value {
            assert!(!doc.contains_key("id"));
            assert!(doc.contains_key("name"));
            assert!(doc.contains_key("price"));
        } else {
            panic!("Expected BSON Document");
        }
    }

    #[test]
    fn test_timestamp_document_structure() {
        let test_ids = vec![
            Bson::String("id1".to_string()),
            Bson::String("id2".to_string()),
            Bson::String("id3".to_string()),
        ];

        let doc = doc! {
            "timestamp": mongodb::bson::DateTime::now(),
            "ids": test_ids.clone(),
        };

        assert!(doc.contains_key("timestamp"));
        assert!(doc.contains_key("ids"));
        
        let ids_in_doc = doc.get_array("ids").unwrap();
        assert_eq!(ids_in_doc.len(), 3);
        assert_eq!(ids_in_doc[0], Bson::String("id1".to_string()));
        assert_eq!(ids_in_doc[1], Bson::String("id2".to_string()));
        assert_eq!(ids_in_doc[2], Bson::String("id3".to_string()));
    }

    #[test]
    fn test_empty_ids_document() {
        let empty_ids: Vec<Bson> = vec![];
        let doc = doc! {
            "timestamp": mongodb::bson::DateTime::now(),
            "ids": empty_ids,
        };

        assert!(doc.contains_key("timestamp"));
        assert!(doc.contains_key("ids"));
        
        let ids_in_doc = doc.get_array("ids").unwrap();
        assert_eq!(ids_in_doc.len(), 0);
    }

    #[test]
    fn test_multiple_logements_processing() {
        let test_data = json!([
            {
                "id": "test-id-1",
                "name": "Test Logement 1",
                "price": 300
            },
            {
                "id": "test-id-2", 
                "name": "Test Logement 2",
                "price": 400
            },
            {
                "name": "Logement Without ID",
                "price": 250
            }
        ]);

        if let Some(items_array) = test_data.as_array() {
            assert_eq!(items_array.len(), 3);
            
            // First item has ID
            let first_item = &items_array[0];
            let first_bson = mongodb::bson::to_bson(first_item).unwrap();
            if let Bson::Document(doc) = first_bson {
                assert!(doc.contains_key("id"));
                assert_eq!(doc.get_str("id").unwrap(), "test-id-1");
            }

            // Third item missing ID
            let third_item = &items_array[2];
            let third_bson = mongodb::bson::to_bson(third_item).unwrap();
            if let Bson::Document(doc) = third_bson {
                assert!(!doc.contains_key("id"));
                assert_eq!(doc.get_str("name").unwrap(), "Logement Without ID");
            }
        } else {
            panic!("Expected array");
        }
    }

    // Integration test that would work with a real MongoDB instance
    // Commented out as it requires a running MongoDB instance
    /*
    #[tokio::test]
    #[ignore] // Use `cargo test -- --ignored` to run this test
    async fn test_full_mongodb_integration() {
        // This test requires MONGODB_URI environment variable to be set
        // Example: MONGODB_URI=mongodb://localhost:27017
        
        let mongodb_uri = std::env::var("MONGODB_URI_TEST")
            .unwrap_or_else(|_| "mongodb://localhost:27017".to_string());
        
        let client_result = connect_to_mongodb(&mongodb_uri).await;
        if client_result.is_err() {
            println!("Skipping integration test - MongoDB not available");
            return;
        }
        
        let client = client_result.unwrap();
        
        // Test data insertion
        let test_data = json!([
            {
                "id": "integration-test-1",
                "name": "Integration Test Logement",
                "price": 350
            }
        ]);
        
        let insert_result = insert_logements_into_mongodb(&client, &test_data).await;
        assert!(insert_result.is_ok());
        
        let ids = insert_result.unwrap();
        assert_eq!(ids.len(), 1);
        
        // Test timestamp insertion
        let timestamp_result = insert_ids_with_timestamp_into_mongodb(&client, ids).await;
        assert!(timestamp_result.is_ok());
        
        // Cleanup - remove test data
        let logements_collection = client.database("CROUS").collection::<Document>("logements");
        let available_collection = client.database("CROUS").collection::<Document>("available");
        
        logements_collection.delete_many(doc! { "_id": "integration-test-1" }, None).await.unwrap();
        available_collection.delete_many(doc! {}, None).await.unwrap();
    }
    */
}
