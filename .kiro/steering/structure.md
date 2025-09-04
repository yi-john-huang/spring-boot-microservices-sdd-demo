# Project Structure

## Root Level Organization
```
├── auth-service/          # Authentication & authorization service
├── config-server/         # Spring Cloud Config Server
├── eureka-server/         # Service discovery server
├── file-storage/          # File upload/download service
├── gateway/               # API Gateway (entry point)
├── job-service/           # Core business logic (jobs, adverts, offers)
├── notification-service/  # Kafka-based notification system
├── user-service/          # User management service
├── config/               # Shared configuration files
├── screenshots/          # Documentation images
└── docker-compose.yml    # Infrastructure setup
```

## Service Structure Pattern
Each microservice follows standard Spring Boot Maven structure:
```
service-name/
├── src/main/java/com/safalifter/servicename/
│   ├── ServiceNameApplication.java    # Main application class
│   ├── client/                        # Feign clients for inter-service calls
│   ├── config/                        # Configuration classes
│   ├── controller/                    # REST controllers
│   ├── dto/                          # Data Transfer Objects
│   ├── enums/                        # Enum definitions
│   ├── exc/                          # Exception handling
│   ├── jwt/                          # JWT utilities (where applicable)
│   ├── model/                        # JPA entities
│   ├── repository/                   # Data access repositories
│   ├── request/                      # Request objects
│   └── service/                      # Business logic
├── src/main/resources/
│   └── application.properties        # Service configuration
└── pom.xml                          # Maven dependencies
```

## Package Naming Convention
- Base package: `com.safalifter.{servicename}`
- Follow standard Spring Boot layered architecture
- Use descriptive folder names for request objects (e.g., `request/advert/`, `request/job/`)

## Configuration Management
- **Local config**: Each service has `application.properties`
- **Centralized config**: Config server manages shared properties
- **External config**: `config/application.properties` for shared settings
- Services connect to config server via `spring.config.import=configserver:http://localhost:8888/`

## API Versioning
- All endpoints use `/v1/` prefix
- Service-specific routing: `/v1/{service-name}/endpoint`
- Gateway routes requests to appropriate services

## Common Patterns
- **DTOs**: Separate request/response objects from entities
- **ModelMapper**: Used for entity-DTO conversions
- **Exception Handling**: Centralized via `GeneralExceptionHandler`
- **Security**: JWT filters in services that require authentication
- **Validation**: Bean validation on request objects
- **File Handling**: Multipart form data for file uploads

## Inter-Service Communication
- **Feign Clients**: Declarative REST clients in `client/` package
- **Error Handling**: Custom error decoders for Feign clients
- **Service Discovery**: Services register with Eureka for discovery