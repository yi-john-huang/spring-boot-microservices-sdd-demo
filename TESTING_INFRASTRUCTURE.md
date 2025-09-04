# Testing Infrastructure Setup

This document describes the comprehensive testing infrastructure that has been set up across all microservices in the job marketplace platform.

## Overview

The testing infrastructure includes:
- **JaCoCo** for code coverage analysis and reporting
- **AssertJ** for fluent assertions
- **TestContainers** for integration testing with real databases
- **WireMock** for mocking external HTTP services
- **H2** for fast in-memory database testing
- **Maven profiles** for different test execution scenarios

## Dependencies Added

### Core Testing Dependencies
- `assertj-core` (3.24.2) - Fluent assertions library
- `testcontainers-junit-jupiter` (1.19.0) - TestContainers integration
- `testcontainers-postgresql` (1.19.0) - PostgreSQL TestContainer
- `testcontainers-kafka` (1.19.0) - Kafka TestContainer (job-service, notification-service)
- `wiremock-jre8` (2.35.0) - HTTP service mocking
- `h2` - In-memory database for fast unit tests
- `spring-security-test` - Security testing utilities

### Coverage and Build Tools
- `jacoco-maven-plugin` (0.8.8) - Code coverage analysis
- `maven-surefire-plugin` - Unit test execution
- `maven-failsafe-plugin` - Integration test execution

## Maven Profiles

### 1. unit-tests (Default)
```bash
mvn test
# or
mvn test -P unit-tests
```
- Runs only unit tests (`**/*Test.java`)
- Excludes integration tests (`**/*IntegrationTest.java`)
- Fast execution for development feedback

### 2. integration-tests
```bash
mvn test -P integration-tests
```
- Runs only integration tests (`**/*IntegrationTest.java`)
- Uses TestContainers for realistic testing
- Slower execution but more comprehensive

### 3. coverage
```bash
mvn test -P coverage
```
- Generates JaCoCo coverage reports
- Creates HTML and XML reports in `target/site/jacoco/`
- Useful for coverage analysis

### 4. all-tests
```bash
mvn test -P all-tests
```
- Runs both unit and integration tests
- Comprehensive test execution
- Recommended for CI/CD pipelines

## JaCoCo Configuration

### Coverage Thresholds
- **Line Coverage**: Minimum 80%
- **Branch Coverage**: Minimum 75%

### Exclusions
The following packages/classes are excluded from coverage requirements:
- `**/config/**` - Configuration classes
- `**/dto/**` - Data Transfer Objects
- `**/request/**` - Request objects
- `**/enums/**` - Enum definitions
- `**/*Application.class` - Spring Boot main classes

### Reports
- **HTML Report**: `target/site/jacoco/index.html`
- **XML Report**: `target/site/jacoco/jacoco.xml`
- **CSV Report**: `target/site/jacoco/jacoco.csv`

## Test Configuration Files

Each service includes an `application-test.yml` file with:
- H2 in-memory database configuration
- Disabled Eureka client for isolated testing
- Debug logging for troubleshooting
- Service-specific test configurations

### Example Test Configuration
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

eureka:
  client:
    enabled: false

logging:
  level:
    com.safalifter: DEBUG
```

## Service-Specific Configurations

### Auth Service
- JWT testing configuration
- Security test utilities
- WireMock for external service calls

### User Service
- File upload testing configuration
- Security integration testing
- Database relationship testing

### Job Service
- Kafka testing with embedded Kafka
- Multi-entity relationship testing
- File storage integration testing

### Notification Service
- Kafka consumer testing
- Event-driven testing scenarios
- Message processing validation

### File Storage Service
- Multipart file upload testing
- File system operation testing
- Storage validation testing

### Gateway Service
- WebFlux testing configuration
- Route testing utilities
- JWT filter testing

## Usage Examples

### Running Tests for a Single Service
```bash
cd auth-service
./mvnw test                    # Unit tests only
./mvnw test -P integration-tests  # Integration tests only
./mvnw test -P all-tests          # All tests
./mvnw verify -P coverage        # With coverage report
```

### Running Tests for All Services
```bash
# From root directory
for service in auth-service user-service job-service notification-service file-storage gateway; do
    echo "Testing $service..."
    cd $service
    ./mvnw test
    cd ..
done
```

### Generating Coverage Reports
```bash
cd auth-service
./mvnw clean verify -P coverage
# Open target/site/jacoco/index.html in browser
```

## CI/CD Integration

### Quality Gates
The JaCoCo plugin is configured to fail builds if coverage drops below thresholds:
- Line coverage < 80%
- Branch coverage < 75%

### Recommended CI/CD Pipeline
```yaml
# Example GitHub Actions workflow
- name: Run Unit Tests
  run: mvn test -P unit-tests

- name: Run Integration Tests  
  run: mvn test -P integration-tests

- name: Generate Coverage Report
  run: mvn verify -P coverage

- name: Upload Coverage to Codecov
  uses: codecov/codecov-action@v3
  with:
    file: target/site/jacoco/jacoco.xml
```

## Next Steps

With the testing infrastructure in place, you can now:

1. **Create Base Test Classes** (Task 2.1)
   - `BaseServiceTest` for service layer testing
   - `BaseControllerTest` for controller testing
   - `BaseRepositoryTest` for repository testing

2. **Implement Test Data Builders** (Task 2.2)
   - Builder pattern for creating test entities
   - Consistent test data across services

3. **Create Custom Assertions** (Task 2.3)
   - Domain-specific AssertJ assertions
   - Custom Mockito matchers

4. **Write Comprehensive Unit Tests** (Tasks 3-8)
   - Service layer tests with mocked dependencies
   - Controller tests with MockMvc
   - Repository tests with @DataJpaTest
   - Integration tests with TestContainers

## Troubleshooting

### Common Issues

1. **Maven Wrapper Issues**
   ```bash
   chmod +x mvnw  # Make wrapper executable
   ```

2. **H2 Database Lock Issues**
   - Use `DB_CLOSE_DELAY=-1` in test configuration
   - Ensure proper test isolation with `@DirtiesContext`

3. **TestContainers Docker Issues**
   - Ensure Docker is running
   - Check Docker daemon accessibility

4. **Coverage Report Generation**
   ```bash
   mvn clean verify -P coverage  # Clean before generating reports
   ```

## Verification

All services have been configured with:
- ✅ JaCoCo plugin with 80% line coverage threshold
- ✅ AssertJ for fluent assertions
- ✅ TestContainers for integration testing
- ✅ WireMock for external service mocking
- ✅ Maven profiles for different test scenarios
- ✅ Test configuration files
- ✅ Proper exclusions for coverage analysis

The testing infrastructure is now ready for comprehensive unit test implementation across all microservices.