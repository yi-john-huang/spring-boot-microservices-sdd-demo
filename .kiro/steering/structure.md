# Structure Steering Document

## Project Organization

### Root Directory Structure
```
spring-boot-microservices-sdd-demo/
├── auth-service/           # Authentication and authorization service
├── config-server/          # Centralized configuration management
├── eureka-server/          # Service discovery server
├── gateway/                # API Gateway service
├── job-service/            # Job management service
├── user-service/           # User management service
├── notification-service/   # Notification handling service
├── file-storage/           # File upload and storage service
├── config/                 # Configuration files
├── screenshots/            # Documentation assets
├── docker-compose.yml      # Container orchestration
└── README.md              # Project documentation
```

### Service Structure Pattern
Each microservice follows this standard structure:
```
service-name/
├── src/main/java/com/example/servicename/
│   ├── ServiceNameApplication.java    # Main application class
│   ├── controller/                    # REST controllers
│   ├── service/                       # Business logic layer
│   ├── repository/                    # Data access layer
│   ├── model/                         # Entity classes
│   ├── dto/                          # Data transfer objects
│   ├── config/                       # Configuration classes
│   └── exception/                    # Custom exceptions
├── src/main/resources/
│   ├── application.yml               # Service configuration
│   └── bootstrap.yml                 # Bootstrap configuration
├── src/test/                         # Test classes
├── Dockerfile                        # Container definition
└── pom.xml                          # Maven dependencies
```

## Coding Patterns

### Naming Conventions
- **Services**: `ServiceNameService` (e.g., `UserService`, `JobService`)
- **Controllers**: `ServiceNameController` (e.g., `UserController`)
- **Repositories**: `EntityNameRepository` (e.g., `UserRepository`)
- **DTOs**: `EntityNameDto` (e.g., `UserDto`, `JobDto`)
- **Entities**: `EntityName` (e.g., `User`, `Job`)

### Package Organization
- **Base Package**: `com.example.servicename`
- **Controllers**: `com.example.servicename.controller`
- **Services**: `com.example.servicename.service`
- **Repositories**: `com.example.servicename.repository`
- **Models**: `com.example.servicename.model`
- **DTOs**: `com.example.servicename.dto`
- **Config**: `com.example.servicename.config`

### Configuration Patterns
- **Application Properties**: Use YAML format (`application.yml`)
- **Environment Variables**: For sensitive data and environment-specific configs
- **Config Server**: Centralized configuration for common settings
- **Bootstrap Config**: For service discovery and config server connection

### API Design Patterns
- **RESTful URLs**: `/api/v1/resource` format
- **HTTP Methods**: GET, POST, PUT, DELETE for CRUD operations
- **Response Format**: Consistent JSON response structure
- **Error Handling**: Standard HTTP status codes with error details

### Security Patterns
- **JWT Authentication**: Bearer token in Authorization header
- **Role-based Access**: `@PreAuthorize` annotations for method security
- **CORS Configuration**: Proper cross-origin resource sharing setup
- **Input Validation**: `@Valid` annotations with custom validators

### Database Patterns
- **Entity Relationships**: JPA annotations for associations
- **Repository Pattern**: Spring Data JPA repositories
- **Transaction Management**: `@Transactional` for data consistency
- **Database Migration**: Version-controlled schema changes

## File Naming Rules

### Java Files
- **Classes**: PascalCase (e.g., `UserController.java`)
- **Interfaces**: PascalCase with descriptive names (e.g., `UserRepository.java`)
- **Test Classes**: `ClassNameTest.java` or `ClassNameIT.java` for integration tests

### Configuration Files
- **Application Config**: `application.yml`, `application-{profile}.yml`
- **Bootstrap Config**: `bootstrap.yml`
- **Docker**: `Dockerfile`, `docker-compose.yml`

### Documentation
- **README**: `README.md` in each service directory
- **API Docs**: Generated via Spring Boot Actuator or Swagger
- **Architecture Docs**: Markdown files in `/docs` directory
