# Technology Stack

## Core Framework
- **Spring Boot 2.7.14** with Java 17
- **Spring Cloud 2021.0.8** for microservices architecture
- **Maven** as build system

## Microservices Infrastructure
- **Netflix Eureka Server** - Service discovery and registration
- **Spring Cloud Gateway** - API Gateway with WebFlux (reactive)
- **Spring Cloud Config Server** - Centralized configuration management
- **OpenFeign** - Declarative REST client for inter-service communication

## Security & Authentication
- **Spring Security** with JWT tokens
- **JJWT 0.11.5** for JWT implementation
- Role-based authorization (ADMIN/USER roles)

## Data & Messaging
- **PostgreSQL** - Primary database
- **Spring Data JPA** - Data access layer
- **Apache Kafka** with Zookeeper - Event streaming and notifications
- **Redis** - Caching (mentioned in dependencies)

## Documentation & Validation
- **SpringDoc OpenAPI UI 1.6.15** - API documentation (Swagger)
- **Bean Validation** - Request validation
- **Lombok** - Code generation

## Development Tools
- **ModelMapper** - Object mapping between DTOs and entities
- **Docker & Docker Compose** - Containerization

## Common Build Commands

```bash
# Build individual service
./mvnw clean package

# Run with Maven
./mvnw spring-boot:run

# Docker operations
docker-compose up          # Start infrastructure (PostgreSQL, Kafka, etc.)
docker-compose down        # Stop infrastructure
```

## Service Startup Order
1. `docker-compose up` (PostgreSQL, Kafka, Zookeeper)
2. Eureka Server
3. Gateway
4. Config Server  
5. Business services (auth-service, user-service, job-service, notification-service, file-storage)

## Port Configuration
- Gateway: 8080
- Other services: Dynamic ports (server.port=0)
- PostgreSQL: 5432
- Kafka: 9092
- Kafka UI: 9090
- Eureka Server: 8761 (default)