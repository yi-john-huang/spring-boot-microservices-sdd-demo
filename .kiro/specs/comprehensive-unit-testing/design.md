# Design Document

## Overview

This design outlines a comprehensive unit testing strategy for the Spring Boot microservices job marketplace platform. The solution will implement thorough test coverage across all layers (controllers, services, repositories, utilities) using Spring Boot Test framework, Mockito, and JUnit 5. The design emphasizes consistency, maintainability, and adherence to testing best practices while establishing a foundation for Test-Driven Development (TDD).

## Architecture

### Testing Framework Stack

- **JUnit 5**: Core testing framework with parameterized tests and lifecycle management
- **Mockito**: Mocking framework for dependencies and external services
- **Spring Boot Test**: Integration with Spring context for slice testing
- **TestContainers**: For repository integration tests with real databases
- **WireMock**: For mocking external HTTP services and Feign clients
- **AssertJ**: Fluent assertions for better test readability
- **JaCoCo**: Code coverage analysis and reporting

### Test Architecture Layers

```
Test Architecture
├── Unit Tests (Isolated)
│   ├── Service Layer Tests (@ExtendWith(MockitoExtension.class))
│   ├── Controller Tests (@WebMvcTest)
│   ├── Repository Tests (@DataJpaTest)
│   └── Utility/Component Tests (@ExtendWith(MockitoExtension.class))
├── Integration Tests (Slice Testing)
│   ├── Web Layer Integration (@SpringBootTest + @AutoConfigureTestDatabase)
│   └── Data Layer Integration (@DataJpaTest + @TestPropertySource)
└── Test Infrastructure
    ├── Common Test Utilities
    ├── Test Data Builders
    ├── Custom Matchers
    └── Base Test Classes
```

## Components and Interfaces

### 1. Test Infrastructure Components

#### Base Test Classes
```java
// Abstract base class for service tests
@ExtendWith(MockitoExtension.class)
public abstract class BaseServiceTest {
    protected ModelMapper modelMapper;
    // Common setup and utilities
}

// Abstract base class for controller tests  
@WebMvcTest
public abstract class BaseControllerTest {
    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
    // Common security and validation setup
}

// Abstract base class for repository tests
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseRepositoryTest {
    @Autowired protected TestEntityManager entityManager;
    // Common test data setup
}
```

#### Test Data Builders
```java
// Builder pattern for creating test entities
public class UserTestDataBuilder {
    public static User.UserBuilder defaultUser() { /* implementation */ }
    public static User.UserBuilder adminUser() { /* implementation */ }
    public static User.UserBuilder inactiveUser() { /* implementation */ }
}

public class JobTestDataBuilder {
    public static Job.JobBuilder defaultJob() { /* implementation */ }
    public static Job.JobBuilder jobWithCategory(Category category) { /* implementation */ }
}
```

#### Custom Matchers and Assertions
```java
// Custom AssertJ assertions for domain objects
public class UserAssertions extends AbstractAssert<UserAssertions, User> {
    public UserAssertions hasActiveStatus() { /* implementation */ }
    public UserAssertions hasRole(Role role) { /* implementation */ }
}

// Custom Mockito matchers
public class CustomMatchers {
    public static ArgumentMatcher<RegisterRequest> validRegisterRequest() { /* implementation */ }
}
```

### 2. Service Layer Testing Strategy

#### Authentication Service Tests
```java
@ExtendWith(MockitoExtension.class)
class AuthServiceTest extends BaseServiceTest {
    @Mock private AuthenticationManager authenticationManager;
    @Mock private UserServiceClient userServiceClient;
    @Mock private JwtService jwtService;
    @InjectMocks private AuthService authService;
    
    // Test successful login
    // Test failed authentication
    // Test user registration
    // Test JWT token generation
}
```

#### Business Service Tests Pattern
- Mock all external dependencies (repositories, clients, utilities)
- Test business logic validation and transformations
- Verify exception handling for various error scenarios
- Test data mapping between DTOs and entities
- Verify interactions with mocked dependencies

### 3. Controller Layer Testing Strategy

#### REST Controller Tests
```java
@WebMvcTest(JobController.class)
class JobControllerTest extends BaseControllerTest {
    @MockBean private JobService jobService;
    @MockBean private ModelMapper modelMapper;
    
    // Test successful requests with valid data
    // Test validation errors with invalid data
    // Test security authorization
    // Test exception handling
    // Test multipart file uploads
}
```

#### Security Testing Integration
- Mock JWT authentication for secured endpoints
- Test role-based authorization (@PreAuthorize)
- Verify proper error responses for unauthorized access
- Test CORS and security headers

### 4. Repository Layer Testing Strategy

#### JPA Repository Tests
```java
@DataJpaTest
class UserRepositoryTest extends BaseRepositoryTest {
    @Autowired private UserRepository userRepository;
    
    // Test custom query methods
    // Test entity relationships and cascading
    // Test constraint validations
    // Test pagination and sorting
}
```

#### TestContainers Integration
- Use PostgreSQL TestContainer for realistic database testing
- Test complex queries and database-specific features
- Verify transaction behavior and rollback scenarios

### 5. Feign Client Testing Strategy

#### External Service Client Tests
```java
@ExtendWith(MockitoExtension.class)
class UserServiceClientTest {
    private WireMockServer wireMockServer;
    private UserServiceClient userServiceClient;
    
    // Test successful service calls
    // Test error responses and custom error decoder
    // Test timeout and retry behavior
    // Test request/response serialization
}
```

## Data Models

### Test Configuration Models

#### Test Profiles and Properties
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
  jpa:
    hibernate:
      ddl-auto: create-drop
  kafka:
    bootstrap-servers: ${spring.embedded.kafka.brokers}

logging:
  level:
    com.safalifter: DEBUG
    org.springframework.security: DEBUG
```

#### Coverage Configuration
```xml
<!-- JaCoCo Maven Plugin Configuration -->
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <configuration>
        <rules>
            <rule>
                <element>BUNDLE</element>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.80</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</plugin>
```

## Error Handling

### Test Exception Scenarios

#### Service Layer Exception Testing
- Test business validation exceptions (NotFoundException, ValidationException)
- Test external service failures and fallback behavior
- Test transaction rollback scenarios
- Test concurrent access and optimistic locking

#### Controller Layer Exception Testing
- Test global exception handler responses
- Test validation error formatting
- Test security exception handling
- Test malformed request handling

### Mock Error Simulation
```java
// Simulate external service failures
when(userServiceClient.getUserById(anyString()))
    .thenThrow(new FeignException.ServiceUnavailable("Service unavailable", mock(Request.class), null));

// Simulate database constraints
when(userRepository.save(any(User.class)))
    .thenThrow(new DataIntegrityViolationException("Duplicate key"));
```

## Testing Strategy

### Test Organization and Naming

#### Package Structure
```
src/test/java/com/safalifter/{service}/
├── controller/
│   ├── {Entity}ControllerTest.java
│   └── integration/
├── service/
│   ├── {Entity}ServiceTest.java
│   └── integration/
├── repository/
│   ├── {Entity}RepositoryTest.java
│   └── integration/
├── client/
│   └── {External}ClientTest.java
├── util/
│   └── {Utility}Test.java
└── testutil/
    ├── builders/
    ├── matchers/
    └── BaseTestClasses.java
```

#### Test Naming Convention
- Test class: `{ClassUnderTest}Test`
- Test method: `{methodName}_{scenario}_{expectedResult}`
- Example: `login_WithValidCredentials_ReturnsTokenDto`
- Example: `createJob_WithInvalidData_ThrowsValidationException`

### Test Data Management

#### Test Data Builders Pattern
```java
public class JobTestDataBuilder {
    private Job job;
    
    public static JobTestDataBuilder aJob() {
        return new JobTestDataBuilder();
    }
    
    public JobTestDataBuilder withTitle(String title) {
        this.job.setTitle(title);
        return this;
    }
    
    public JobTestDataBuilder withCategory(Category category) {
        this.job.setCategory(category);
        return this;
    }
    
    public Job build() {
        return job;
    }
}
```

#### Test Database Management
- Use @Sql annotations for complex test data setup
- Implement @DirtiesContext for tests that modify shared state
- Use @Transactional with @Rollback for automatic cleanup

### Coverage and Quality Metrics

#### Coverage Targets
- **Line Coverage**: Minimum 80% across all services
- **Branch Coverage**: Minimum 75% for complex business logic
- **Method Coverage**: 100% for public API methods
- **Class Coverage**: 95% excluding configuration classes

#### Quality Gates
- All tests must pass before merge
- Coverage thresholds enforced in CI/CD pipeline
- No new code without corresponding tests
- Mutation testing for critical business logic

### Continuous Integration Integration

#### Maven Test Execution
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
        <excludes>
            <exclude>**/*IntegrationTest.java</exclude>
        </excludes>
    </configuration>
</plugin>
```

#### Test Profiles
- `test`: Fast unit tests only
- `integration-test`: Include integration tests
- `coverage`: Generate coverage reports
- `mutation-test`: Run mutation testing

This comprehensive testing design ensures robust coverage of all microservice components while establishing consistent patterns and practices for future TDD development.