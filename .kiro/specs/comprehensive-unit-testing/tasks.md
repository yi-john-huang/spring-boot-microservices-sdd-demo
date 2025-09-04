# Implementation Plan

- [x] 1. Set up testing infrastructure and dependencies

  - Add comprehensive testing dependencies to all service pom.xml files (JaCoCo, AssertJ, TestContainers, WireMock)
  - Create Maven profiles for different test execution scenarios (unit, integration, coverage)
  - Configure JaCoCo plugin with coverage thresholds and reporting
  - _Requirements: 6.1, 6.2, 6.3, 6.4_

- [ ] 2. Create common test infrastructure and utilities

  - [ ] 2.1 Implement base test classes for different layers

    - Create BaseServiceTest abstract class with common mocking setup
    - Create BaseControllerTest abstract class with MockMvc and security configuration
    - Create BaseRepositoryTest abstract class with TestEntityManager setup
    - _Requirements: 7.1, 7.2, 7.4_

  - [ ] 2.2 Implement test data builders using builder pattern

    - Create UserTestDataBuilder with various user scenarios (admin, regular, inactive)
    - Create JobTestDataBuilder with job creation scenarios
    - Create AdvertTestDataBuilder and OfferTestDataBuilder for job service entities
    - Create CategoryTestDataBuilder for category management
    - _Requirements: 7.2, 7.5_

  - [ ] 2.3 Create custom assertions and matchers
    - Implement custom AssertJ assertions for domain objects (UserAssertions, JobAssertions)
    - Create custom Mockito argument matchers for complex request validation
    - Implement test utilities for JWT token generation and validation
    - _Requirements: 7.4, 7.5_

- [ ] 3. Implement comprehensive auth-service unit tests

  - [ ] 3.1 Create AuthService unit tests

    - Test successful login with valid credentials and JWT token generation
    - Test failed authentication scenarios and WrongCredentialsException handling
    - Test user registration flow with UserServiceClient interaction
    - Mock AuthenticationManager and verify authentication calls
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 3.2 Create AuthController unit tests

    - Test login endpoint with valid/invalid credentials using MockMvc
    - Test register endpoint with valid/invalid registration data
    - Test request validation and error response formatting
    - Test security configuration and endpoint accessibility
    - _Requirements: 2.1, 2.2, 2.3, 2.5_

  - [ ] 3.3 Create JwtService unit tests

    - Test JWT token generation with user details
    - Test token validation and expiration scenarios
    - Test token parsing and claims extraction
    - Test invalid token handling and security exceptions
    - _Requirements: 4.1, 4.5_

  - [ ] 3.4 Create UserServiceClient unit tests with WireMock
    - Test successful user service calls and response deserialization
    - Test error scenarios and CustomErrorDecoder functionality
    - Test timeout and retry behavior for external service calls
    - Mock external service responses and verify request formatting
    - _Requirements: 5.1, 5.2, 5.3, 5.4_

- [ ] 4. Implement comprehensive user-service unit tests

  - [ ] 4.1 Create UserService unit tests

    - Test user registration with password encoding and default role assignment
    - Test user retrieval by ID, email, and username with NotFoundException scenarios
    - Test user update functionality with file upload integration
    - Test user soft deletion and active status management
    - Mock UserRepository, PasswordEncoder, and FileStorageClient dependencies
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 1.5_

  - [ ] 4.2 Create UserController unit tests

    - Test all CRUD endpoints with MockMvc and proper HTTP status codes
    - Test multipart file upload handling for profile pictures
    - Test request validation for user creation and update requests
    - Test security authorization and role-based access control
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_

  - [ ] 4.3 Create UserRepository unit tests

    - Test custom query methods (findByEmail, findByUsername, findAllByActive)
    - Test entity relationships and cascading operations with UserDetails
    - Test database constraints and validation rules
    - Use @DataJpaTest with TestEntityManager for realistic database testing
    - _Requirements: 3.1, 3.2, 3.3, 3.5_

  - [ ] 4.4 Create FileStorageClient unit tests
    - Test file upload and download operations with WireMock
    - Test error handling for file storage failures
    - Test file deletion and cleanup operations
    - Verify proper multipart request formatting and response handling
    - _Requirements: 5.1, 5.2, 5.3_

- [ ] 5. Implement comprehensive job-service unit tests

  - [ ] 5.1 Create JobService unit tests

    - Test job creation with category validation and file upload integration
    - Test job retrieval methods (getAll, getById, getByCategoryId, getJobsThatFitYourNeeds)
    - Test job update functionality with file replacement logic
    - Test job deletion and proper cleanup of associated data
    - Mock JobRepository, CategoryService, and FileStorageClient dependencies
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 1.5_

  - [ ] 5.2 Create AdvertService unit tests

    - Test advertisement creation with user validation and status management
    - Test advertisement retrieval and filtering by various criteria
    - Test advertisement update with ownership validation
    - Test advertisement status transitions and business rules
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 5.3 Create OfferService unit tests

    - Test offer creation with job and user validation
    - Test offer status management and business workflow
    - Test offer retrieval and filtering by user and job
    - Test notification sending integration via Kafka
    - Mock OfferRepository and Kafka producer dependencies
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 1.5_

  - [ ] 5.4 Create CategoryService unit tests

    - Test category CRUD operations with admin authorization
    - Test category hierarchy and relationship management
    - Test category validation and duplicate prevention
    - Mock CategoryRepository and verify business logic
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 5.5 Create job-service controller unit tests

    - Test JobController endpoints with security and validation
    - Test AdvertController with multipart file handling
    - Test OfferController with user context and authorization
    - Test CategoryController with admin role requirements
    - Use MockMvc to verify HTTP responses and error handling
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 2.5_

  - [ ] 5.6 Create job-service repository unit tests
    - Test JobRepository custom queries and search functionality
    - Test AdvertRepository filtering and pagination
    - Test OfferRepository with complex joins and relationships
    - Test CategoryRepository hierarchy queries
    - Use @DataJpaTest with realistic test data scenarios
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5_

- [ ] 6. Implement comprehensive notification-service unit tests

  - [ ] 6.1 Create NotificationService unit tests

    - Test notification creation and persistence
    - Test notification retrieval and filtering by user
    - Test notification status management and read/unread functionality
    - Mock NotificationRepository and verify business logic
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 6.2 Create NotificationListener unit tests

    - Test Kafka message consumption and processing
    - Test notification creation from incoming messages
    - Test error handling for malformed messages
    - Use @EmbeddedKafka for realistic message testing
    - _Requirements: 1.1, 1.3, 1.4_

  - [ ] 6.3 Create NotificationController unit tests
    - Test notification retrieval endpoints with user context
    - Test notification status update operations
    - Test security and authorization for notification access
    - _Requirements: 2.1, 2.2, 2.4, 2.5_

- [ ] 7. Implement comprehensive file-storage unit tests

  - [ ] 7.1 Create StorageService unit tests

    - Test file upload with validation and storage logic
    - Test file download and streaming functionality
    - Test file deletion and cleanup operations
    - Test file metadata management and database persistence
    - Mock FileRepository and file system operations
    - _Requirements: 1.1, 1.2, 1.3, 1.4_

  - [ ] 7.2 Create StorageController unit tests

    - Test file upload endpoint with multipart handling
    - Test file download endpoint with proper headers and streaming
    - Test file deletion with authorization checks
    - Test error handling for invalid files and storage failures
    - _Requirements: 2.1, 2.2, 2.3, 2.5_

  - [ ] 7.3 Create FileRepository unit tests
    - Test file metadata persistence and retrieval
    - Test file search and filtering operations
    - Test file cleanup and orphaned file detection
    - _Requirements: 3.1, 3.2, 3.3_

- [ ] 8. Implement gateway and infrastructure service tests

  - [ ] 8.1 Create JwtAuthenticationFilter unit tests

    - Test JWT token extraction from requests
    - Test token validation and user context creation
    - Test filter chain execution and security context setup
    - Test error handling for invalid or expired tokens
    - _Requirements: 4.1, 4.5_

  - [ ] 8.2 Create JwtUtil unit tests

    - Test JWT token parsing and validation logic
    - Test claims extraction and user information retrieval
    - Test token expiration and security validation
    - _Requirements: 4.1, 4.5_

  - [ ] 8.3 Create GatewayConfig unit tests
    - Test route configuration and request routing logic
    - Test security filter integration and authentication flow
    - Test CORS configuration and header management
    - _Requirements: 4.3, 4.4_

- [ ] 9. Set up coverage reporting and quality gates

  - [ ] 9.1 Configure JaCoCo coverage reporting

    - Set up coverage thresholds for each service (minimum 80% line coverage)
    - Configure coverage exclusions for configuration and DTO classes
    - Generate HTML and XML coverage reports for CI/CD integration
    - _Requirements: 6.1, 6.2, 6.3_

  - [ ] 9.2 Create Maven test execution profiles

    - Configure surefire plugin for unit test execution
    - Set up failsafe plugin for integration test execution
    - Create test profiles for different execution scenarios (fast, full, coverage)
    - _Requirements: 6.4, 6.5_

  - [ ] 9.3 Implement test quality validation
    - Add test naming convention validation
    - Configure test execution order and parallel execution
    - Set up test result reporting and failure analysis
    - _Requirements: 7.3, 7.5_

- [ ] 10. Create integration test suites for critical workflows

  - [ ] 10.1 Create end-to-end authentication workflow tests

    - Test complete user registration and login flow
    - Test JWT token lifecycle and refresh scenarios
    - Test role-based authorization across services
    - _Requirements: 1.1, 1.2, 2.4, 4.1_

  - [ ] 10.2 Create job marketplace workflow integration tests

    - Test complete job posting and offer creation workflow
    - Test notification delivery for job-related events
    - Test file upload and association with jobs and users
    - _Requirements: 1.1, 1.5, 5.5_

  - [ ] 10.3 Create cross-service communication integration tests
    - Test Feign client interactions between services
    - Test error propagation and fallback mechanisms
    - Test service discovery and load balancing scenarios
    - _Requirements: 5.1, 5.2, 5.4, 5.5_
