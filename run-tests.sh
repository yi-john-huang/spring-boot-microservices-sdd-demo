#!/bin/bash

# Test execution script for microservices

echo "🧪 Running comprehensive test suite..."

# Function to run tests for a service
run_service_tests() {
    local service=$1
    echo "📋 Testing $service..."
    cd "$service" || exit 1
    mvn clean test -q
    local exit_code=$?
    cd ..
    return $exit_code
}

# Services to test
services=("auth-service" "user-service" "job-service" "notification-service" "file-storage" "gateway")

# Run tests for each service
failed_services=()
for service in "${services[@]}"; do
    if ! run_service_tests "$service"; then
        failed_services+=("$service")
    fi
done

# Generate aggregate coverage report
echo "📊 Generating aggregate coverage report..."
mvn jacoco:merge jacoco:report -P coverage-report -q

# Report results
if [ ${#failed_services[@]} -eq 0 ]; then
    echo "✅ All tests passed!"
    echo "📊 Coverage report available at: target/site/jacoco-aggregate/index.html"
    exit 0
else
    echo "❌ Tests failed in: ${failed_services[*]}"
    exit 1
fi
