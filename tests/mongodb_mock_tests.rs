// Integration tests for MongoDB mocking
// This file demonstrates comprehensive testing strategies for MongoDB operations

use mongodb::bson::{doc, Bson, Document};
use serde_json::json;
use std::collections::HashMap;
use std::sync::{Arc, Mutex};

/// Mock MongoDB Client for testing
/// This struct simulates MongoDB operations without requiring a real database connection
#[derive(Clone)]
pub struct MockMongoClient {
    pub databases: Arc<Mutex<HashMap<String, MockDatabase>>>,
    pub connection_errors: Arc<Mutex<Vec<String>>>,
}

#[derive(Clone)]
pub struct MockDatabase {
    pub collections: HashMap<String, MockCollection>,
}

#[derive(Clone)]
pub struct MockCollection {
    pub documents: HashMap<String, Document>,
    pub operations: Vec<String>,
}

impl MockMongoClient {
    pub fn new() -> Self {
        Self {
            databases: Arc::new(Mutex::new(HashMap::new())),
            connection_errors: Arc::new(Mutex::new(Vec::new())),
        }
    }

    pub fn add_connection_error(&self, error: String) {
        self.connection_errors.lock().unwrap().push(error);
    }

    pub fn get_database(&self, name: &str) -> MockDatabase {
        let mut databases = self.databases.lock().unwrap();
        databases
            .entry(name.to_string())
            .or_insert_with(|| MockDatabase {
                collections: HashMap::new(),
            })
            .clone()
    }

    pub fn get_collection(&self, db_name: &str, collection_name: &str) -> MockCollection {
        let mut databases = self.databases.lock().unwrap();
        let database = databases
            .entry(db_name.to_string())
            .or_insert_with(|| MockDatabase {
                collections: HashMap::new(),
            });

        database
            .collections
            .entry(collection_name.to_string())
            .or_insert_with(|| MockCollection {
                documents: HashMap::new(),
                operations: Vec::new(),
            })
            .clone()
    }

    pub fn simulate_replace_one_upsert(
        &self,
        db_name: &str,
        collection_name: &str,
        filter: Document,
        replacement: Document,
    ) -> Result<(), String> {
        let mut databases = self.databases.lock().unwrap();
        let database = databases
            .entry(db_name.to_string())
            .or_insert_with(|| MockDatabase {
                collections: HashMap::new(),
            });

        let collection = database
            .collections
            .entry(collection_name.to_string())
            .or_insert_with(|| MockCollection {
                documents: HashMap::new(),
                operations: Vec::new(),
            });

        // Extract the filter ID
        if let Ok(id) = filter.get_str("_id") {
            collection.documents.insert(id.to_string(), replacement);
            collection
                .operations
                .push(format!("replace_one_upsert: {}", id));
            Ok(())
        } else {
            Err("Filter must contain _id field".to_string())
        }
    }

    pub fn simulate_insert_one(
        &self,
        db_name: &str,
        collection_name: &str,
        document: Document,
    ) -> Result<(), String> {
        let mut databases = self.databases.lock().unwrap();
        let database = databases
            .entry(db_name.to_string())
            .or_insert_with(|| MockDatabase {
                collections: HashMap::new(),
            });

        let collection = database
            .collections
            .entry(collection_name.to_string())
            .or_insert_with(|| MockCollection {
                documents: HashMap::new(),
                operations: Vec::new(),
            });

        // Generate a unique ID for the document
        let doc_id = format!("doc_{}", collection.documents.len());
        collection.documents.insert(doc_id.clone(), document);
        collection
            .operations
            .push(format!("insert_one: {}", doc_id));
        Ok(())
    }

    pub fn get_document(&self, db_name: &str, collection_name: &str, id: &str) -> Option<Document> {
        let databases = self.databases.lock().unwrap();
        databases
            .get(db_name)?
            .collections
            .get(collection_name)?
            .documents
            .get(id)
            .cloned()
    }

    pub fn get_operations(&self, db_name: &str, collection_name: &str) -> Vec<String> {
        let databases = self.databases.lock().unwrap();
        databases
            .get(db_name)
            .and_then(|db| db.collections.get(collection_name))
            .map(|col| col.operations.clone())
            .unwrap_or_default()
    }

    pub fn collection_size(&self, db_name: &str, collection_name: &str) -> usize {
        let databases = self.databases.lock().unwrap();
        databases
            .get(db_name)
            .and_then(|db| db.collections.get(collection_name))
            .map(|col| col.documents.len())
            .unwrap_or(0)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[tokio::test]
    async fn test_mock_mongodb_basic_operations() {
        let mock_client = MockMongoClient::new();

        // Test document insertion
        let test_doc = doc! {
            "name": "Test Document",
            "value": 42,
            "active": true
        };

        let result = mock_client.simulate_insert_one("test_db", "test_collection", test_doc.clone());
        assert!(result.is_ok());

        // Verify document was stored
        assert_eq!(mock_client.collection_size("test_db", "test_collection"), 1);

        // Check operations log
        let operations = mock_client.get_operations("test_db", "test_collection");
        assert_eq!(operations.len(), 1);
        assert!(operations[0].starts_with("insert_one:"));
    }

    #[tokio::test]
    async fn test_mock_mongodb_upsert_operations() {
        let mock_client = MockMongoClient::new();

        // Test upsert (insert)
        let filter = doc! { "_id": "test-id-1" };
        let document = doc! {
            "_id": "test-id-1",
            "name": "Initial Document",
            "value": 100i64
        };

        let result =
            mock_client.simulate_replace_one_upsert("test_db", "logements", filter.clone(), document);
        assert!(result.is_ok());
        assert_eq!(mock_client.collection_size("test_db", "logements"), 1);

        // Test upsert (replace)
        let updated_document = doc! {
            "_id": "test-id-1",
            "name": "Updated Document",
            "value": 200i64
        };

        let result = mock_client.simulate_replace_one_upsert(
            "test_db",
            "logements",
            filter,
            updated_document.clone(),
        );
        assert!(result.is_ok());
        assert_eq!(mock_client.collection_size("test_db", "logements"), 1); // Still 1 document

        // Verify document was updated
        let stored_doc = mock_client
            .get_document("test_db", "logements", "test-id-1")
            .unwrap();
        assert_eq!(stored_doc.get_str("name").unwrap(), "Updated Document");
        assert_eq!(stored_doc.get_i64("value").unwrap(), 200); // Use i64 instead of i32

        // Check operations log
        let operations = mock_client.get_operations("test_db", "logements");
        assert_eq!(operations.len(), 2);
        assert_eq!(operations[0], "replace_one_upsert: test-id-1");
        assert_eq!(operations[1], "replace_one_upsert: test-id-1");
    }

    #[tokio::test]
    async fn test_mock_mongodb_multiple_collections() {
        let mock_client = MockMongoClient::new();

        // Insert into logements collection
        let logement_doc = doc! {
            "_id": "logement-1",
            "name": "Student Housing",
            "price": 350i64
        };
        mock_client
            .simulate_replace_one_upsert(
                "CROUS",
                "logements",
                doc! { "_id": "logement-1" },
                logement_doc,
            )
            .unwrap();

        // Insert into available collection
        let timestamp_doc = doc! {
            "timestamp": mongodb::bson::DateTime::now(),
            "ids": vec![Bson::String("logement-1".to_string())]
        };
        mock_client
            .simulate_insert_one("CROUS", "available", timestamp_doc)
            .unwrap();

        // Verify both collections exist and have data
        assert_eq!(mock_client.collection_size("CROUS", "logements"), 1);
        assert_eq!(mock_client.collection_size("CROUS", "available"), 1);

        // Verify operations were logged for both collections
        let logements_ops = mock_client.get_operations("CROUS", "logements");
        let available_ops = mock_client.get_operations("CROUS", "available");

        assert_eq!(logements_ops.len(), 1);
        assert_eq!(available_ops.len(), 1);
        assert!(logements_ops[0].contains("replace_one_upsert"));
        assert!(available_ops[0].contains("insert_one"));
    }

    #[tokio::test]
    async fn test_mock_mongodb_concurrent_operations() {
        let mock_client = Arc::new(MockMongoClient::new());

        // Simulate concurrent insertions
        let mut handles = vec![];

        for i in 0..10 {
            let client_clone = Arc::clone(&mock_client);
            let handle = tokio::spawn(async move {
                let doc = doc! {
                    "_id": format!("concurrent-{}", i),
                    "name": format!("Concurrent Document {}", i),
                    "index": i as i64
                };

                client_clone
                    .simulate_replace_one_upsert(
                        "test_db",
                        "concurrent_test",
                        doc! { "_id": format!("concurrent-{}", i) },
                        doc,
                    )
                    .unwrap();
            });
            handles.push(handle);
        }

        // Wait for all operations to complete
        for handle in handles {
            handle.await.unwrap();
        }

        // Verify all documents were inserted
        assert_eq!(mock_client.collection_size("test_db", "concurrent_test"), 10);

        // Verify all operations were logged
        let operations = mock_client.get_operations("test_db", "concurrent_test");
        assert_eq!(operations.len(), 10);

        // Verify all documents exist
        for i in 0..10 {
            let doc = mock_client
                .get_document("test_db", "concurrent_test", &format!("concurrent-{}", i))
                .unwrap();
            assert_eq!(doc.get_i64("index").unwrap(), i as i64); // Convert to i64
        }
    }

    #[tokio::test]
    async fn test_mock_mongodb_error_scenarios() {
        let mock_client = MockMongoClient::new();

        // Test invalid filter (missing _id)
        let invalid_filter = doc! { "name": "invalid" };
        let document = doc! { "name": "Test" };

        let result = mock_client.simulate_replace_one_upsert(
            "test_db",
            "test_collection",
            invalid_filter,
            document,
        );
        assert!(result.is_err());
        assert_eq!(result.unwrap_err(), "Filter must contain _id field");

        // Test connection errors
        mock_client.add_connection_error("Connection timeout".to_string());
        mock_client.add_connection_error("Authentication failed".to_string());

        let errors = mock_client.connection_errors.lock().unwrap();
        assert_eq!(errors.len(), 2);
        assert_eq!(errors[0], "Connection timeout");
        assert_eq!(errors[1], "Authentication failed");
    }

    #[tokio::test]
    async fn test_mock_mongodb_complex_documents() {
        let mock_client = MockMongoClient::new();

        // Test with complex nested document structure
        let complex_doc = doc! {
            "_id": "complex-1",
            "name": "Complex Student Housing",
            "price": 450i64,
            "location": {
                "city": "Paris",
                "district": "15ème",
                "coordinates": [2.3522, 48.8566],
                "metro_lines": ["8", "12"]
            },
            "amenities": [
                {
                    "type": "kitchen",
                    "shared": true,
                    "equipment": ["refrigerator", "stove", "microwave"]
                },
                {
                    "type": "bathroom",
                    "shared": false,
                    "equipment": ["shower", "toilet", "sink"]
                }
            ],
            "availability": {
                "start_date": "2024-09-01",
                "end_date": "2025-06-30",
                "renewable": true
            },
            "metadata": {
                "last_updated": mongodb::bson::DateTime::now(),
                "views": 250i64,
                "favorites": 15i64
            }
        };

        let result = mock_client.simulate_replace_one_upsert(
            "CROUS",
            "logements",
            doc! { "_id": "complex-1" },
            complex_doc.clone(),
        );
        assert!(result.is_ok());

        // Verify complex document was stored correctly
        let stored_doc = mock_client
            .get_document("CROUS", "logements", "complex-1")
            .unwrap();

        assert_eq!(stored_doc.get_str("name").unwrap(), "Complex Student Housing");
        assert_eq!(stored_doc.get_i64("price").unwrap(), 450); // Use i64 instead of i32

        // Verify nested document structure
        let location = stored_doc.get_document("location").unwrap();
        assert_eq!(location.get_str("city").unwrap(), "Paris");

        let amenities = stored_doc.get_array("amenities").unwrap();
        assert_eq!(amenities.len(), 2);

        let availability = stored_doc.get_document("availability").unwrap();
        assert_eq!(availability.get_str("start_date").unwrap(), "2024-09-01");
    }

    #[tokio::test]
    async fn test_mock_mongodb_logement_processing_simulation() {
        let mock_client = MockMongoClient::new();

        // Simulate the actual logement processing workflow
        let test_logements = json!([
            {
                "id": "logement-001",
                "name": "Résidence Universitaire Alpha",
                "price": 320i64,
                "city": "Paris",
                "available": true
            },
            {
                "id": "logement-002",
                "name": "Résidence Universitaire Beta",
                "price": 380i64,
                "city": "Lyon",
                "available": true
            },
            {
                "name": "Invalid Logement (No ID)",
                "price": 250i64,
                "city": "Marseille"
            }
        ]);

        let mut processed_ids = Vec::new();

        if let Some(items_array) = test_logements.as_array() {
            for logement in items_array {
                let bson_value = mongodb::bson::to_bson(logement).unwrap();
                if let Bson::Document(mut doc) = bson_value {
                    if let Some(id_value) = doc.get("id").cloned() {
                        doc.insert("_id", id_value.clone());

                        if let Bson::String(id_str) = &id_value {
                            let filter = doc! { "_id": id_str };
                            mock_client
                                .simulate_replace_one_upsert("CROUS", "logements", filter, doc)
                                .unwrap();
                            processed_ids.push(id_value);
                        }
                    }
                }
            }
        }

        // Simulate timestamp insertion
        let timestamp_doc = doc! {
            "timestamp": mongodb::bson::DateTime::now(),
            "ids": processed_ids.clone(),
        };
        mock_client
            .simulate_insert_one("CROUS", "available", timestamp_doc)
            .unwrap();

        // Verify results
        assert_eq!(mock_client.collection_size("CROUS", "logements"), 2); // Only 2 valid logements
        assert_eq!(mock_client.collection_size("CROUS", "available"), 1); // 1 timestamp entry
        assert_eq!(processed_ids.len(), 2); // 2 processed IDs

        // Verify specific documents
        let doc1 = mock_client
            .get_document("CROUS", "logements", "logement-001")
            .unwrap();
        assert_eq!(doc1.get_str("name").unwrap(), "Résidence Universitaire Alpha");
        assert_eq!(doc1.get_i64("price").unwrap(), 320); // Use i64 instead of i32

        let doc2 = mock_client
            .get_document("CROUS", "logements", "logement-002")
            .unwrap();
        assert_eq!(doc2.get_str("name").unwrap(), "Résidence Universitaire Beta");
        assert_eq!(doc2.get_i64("price").unwrap(), 380); // Use i64 instead of i32

        // Verify operations log
        let logements_ops = mock_client.get_operations("CROUS", "logements");
        let available_ops = mock_client.get_operations("CROUS", "available");

        assert_eq!(logements_ops.len(), 2);
        assert_eq!(available_ops.len(), 1);
    }

    #[tokio::test]
    async fn test_mock_mongodb_performance_simulation() {
        let mock_client = Arc::new(MockMongoClient::new());

        // Simulate processing a large batch of logements
        let start_time = std::time::Instant::now();

        let mut handles = vec![];
        const BATCH_SIZE: usize = 100;

        for i in 0..BATCH_SIZE {
            let client_clone = Arc::clone(&mock_client);
            let handle = tokio::spawn(async move {
                let doc = doc! {
                    "_id": format!("perf-test-{:04}", i),
                    "name": format!("Performance Test Logement {}", i),
                    "price": 300 + (i as i32 * 10),
                    "city": if i % 3 == 0 { "Paris" } else if i % 3 == 1 { "Lyon" } else { "Marseille" },
                    "batch_id": (i / 10) as i32,
                    "available": true
                };

                client_clone
                    .simulate_replace_one_upsert(
                        "CROUS",
                        "performance_test",
                        doc! { "_id": format!("perf-test-{:04}", i) },
                        doc,
                    )
                    .unwrap();
            });
            handles.push(handle);
        }

        // Wait for all operations to complete
        for handle in handles {
            handle.await.unwrap();
        }

        let elapsed = start_time.elapsed();

        // Verify all documents were processed
        assert_eq!(
            mock_client.collection_size("CROUS", "performance_test"),
            BATCH_SIZE
        );

        // Verify operations were logged
        let operations = mock_client.get_operations("CROUS", "performance_test");
        assert_eq!(operations.len(), BATCH_SIZE);

        // Performance assertion (should complete quickly with mocked operations)
        assert!(
            elapsed.as_millis() < 5000,
            "Mock operations took too long: {:?}",
            elapsed
        );

        println!(
            "Processed {} documents in {:?} ({:.2} docs/ms)",
            BATCH_SIZE,
            elapsed,
            BATCH_SIZE as f64 / elapsed.as_millis() as f64
        );
    }
}
