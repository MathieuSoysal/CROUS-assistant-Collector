```mermaid
classDiagram
    %% ===================
    %% DOMAIN CORE (CENTER)
    %% ===================
    
    class Logement {
        +id: String
        +name: String
        +price: f64
        +location: Option~String~
        +new(id: String, name: String, price: f64) Logement
        +validate() Result~(), DomainError~
    }

    class LogementCollection {
        +logements: Vec~Logement~
        +created_at: DateTime
        +new() LogementCollection
        +add_logement(logement: Logement)
        +get_ids() Vec~String~
        +size() usize
        +is_empty() bool
    }

    class LogementDomainService {
        +validate_collection(collection: &LogementCollection) Result~(), DomainError~
        +calculate_statistics(collection: &LogementCollection) LogementStats
    }

    class LogementStats {
        +total_count: usize
        +average_price: f64
    }

    class DomainError {
        <<enumeration>>
        InvalidData(String)
        ValidationFailed(String)
    }

    %% ===================
    %% PORTS (INTERFACES)
    %% ===================
    
    %% Primary Port (Inbound)
    class LogementUseCase {
        <<interface>>
        +collect_and_store() Result~CollectionResult, AppError~
        +get_statistics() Result~LogementStats, AppError~
    }

    class CollectionResult {
        +processed_count: usize
        +stored_count: usize
    }

    %% Secondary Ports (Outbound)
    class LogementRepository {
        <<interface>>
        +save_logements(logements: &[Logement]) Result~Vec~String~, RepoError~
        +save_snapshot(ids: Vec~String~) Result~(), RepoError~
    }

    class DataProvider {
        <<interface>>
        +fetch_logements() Result~LogementCollection, DataError~
    }

    %% ===================
    %% APPLICATION LAYER
    %% ===================
    
    class LogementService {
        -domain_service: LogementDomainService
        -repository: Box~dyn LogementRepository~
        -data_provider: Box~dyn DataProvider~
        +new(domain_service: LogementDomainService, repository: Box~dyn LogementRepository~, data_provider: Box~dyn DataProvider~) LogementService
        +collect_and_store() Result~CollectionResult, AppError~
        +get_statistics() Result~LogementStats, AppError~
    }

    %% ===================
    %% ADAPTERS (INFRASTRUCTURE)
    %% ===================
    
    %% Primary Adapter (Driving)
    class CliApp {
        -use_case: Box~dyn LogementUseCase~
        +new(use_case: Box~dyn LogementUseCase~) CliApp
        +run() Result~(), CliError~
    }

    %% Secondary Adapters (Driven)
    class MongoRepository {
        -client: mongodb::Client
        -database: String
        +new(client: mongodb::Client, database: String) MongoRepository
        +save_logements(logements: &[Logement]) Result~Vec~String~, RepoError~
        +save_snapshot(ids: Vec~String~) Result~(), RepoError~
    }

    class CrousApi {
        -http_client: reqwest::Client
        -base_url: String
        +new(base_url: String) CrousApi
        +fetch_logements() Result~LogementCollection, DataError~
    }

    %% ===================
    %% CONFIGURATION
    %% ===================
    
    class AppConfig {
        +mongodb_uri: String
        +crous_url: String
        +from_env() Result~AppConfig, ConfigError~
    }

    %% ===================
    %% ERROR TYPES
    %% ===================
    
    class AppError {
        <<enumeration>>
        Domain(DomainError)
        Repository(RepoError)
        DataProvider(DataError)
    }

    class RepoError {
        <<enumeration>>
        ConnectionFailed(String)
        InsertFailed(String)
    }

    class DataError {
        <<enumeration>>
        HttpError(String)
        ParseError(String)
    }

    class ConfigError {
        <<enumeration>>
        MissingEnvVar(String)
        InvalidValue(String)
    }

    class CliError {
        <<enumeration>>
        AppError(AppError)
        ConfigError(ConfigError)
    }

    %% ===================
    %% DEPENDENCY INJECTION
    %% ===================
    
    class AppBuilder {
        +new() AppBuilder
        +build_from_config(config: AppConfig) Result~CliApp, AppError~
    }

    %% ===================
    %% RELATIONSHIPS
    %% ===================
    
    %% Domain relationships
    LogementCollection "1" *-- "0..*" Logement
    LogementDomainService --> LogementCollection : operates on
    LogementDomainService --> LogementStats : produces

    %% Port implementations
    LogementService ..|> LogementUseCase : implements
    MongoRepository ..|> LogementRepository : implements
    CrousApi ..|> DataProvider : implements

    %% Application Service dependencies
    LogementService --> LogementDomainService : uses
    LogementService --> LogementRepository : depends on
    LogementService --> DataProvider : depends on

    %% Primary Adapter
    CliApp --> LogementUseCase : drives

    %% Error hierarchy
    AppError --> DomainError
    AppError --> RepoError
    AppError --> DataError
    CliError --> AppError
    CliError --> ConfigError

    %% Builder
    AppBuilder --> CliApp : creates
    AppBuilder --> LogementService : creates
    AppBuilder --> MongoRepository : creates
    AppBuilder --> CrousApi : creates
```

## Simplified Hexagonal Architecture

### Core Architecture:

1. **Domain Core**: `Logement`, `LogementCollection`, `LogementDomainService`
2. **Primary Port**: `LogementUseCase` (what the app can do)
3. **Secondary Ports**: `LogementRepository`, `DataProvider` (what the app needs)
4. **Application Service**: `LogementService` (orchestrates domain + external dependencies)
5. **Adapters**: `CliApp` (primary), `MongoRepository` + `CrousApi` (secondary)

### Key Simplifications:

- **Removed excessive value objects** (Money, Currency, etc.) - keeping it simple with primitives
- **Consolidated error types** - fewer, more focused error categories
- **Simplified configuration** - basic environment-based config
- **Removed notification complexity** - focus on core functionality
- **Single primary adapter** - CLI only (can add REST later if needed)
- **Streamlined domain model** - essential properties only

### Benefits Maintained:

- ✅ **Domain independence** - business logic isolated
- ✅ **Testability** - easy to mock ports
- ✅ **Flexibility** - can swap implementations
- ✅ **Clean dependencies** - inward-pointing dependencies
- ✅ **Separation of concerns** - clear layer boundaries

This simplified version maintains hexagonal architecture principles while being much easier to understand and implement.