#!/bin/bash

# Integration tests only execution script

echo "🧪 Running integration tests only..."

services=("auth-service" "user-service" "job-service" "notification-service" "file-storage" "gateway")

for service in "${services[@]}"; do
    echo "📋 Integration testing $service..."
    cd "$service" || exit 1
    mvn clean test -P integration-tests -q
    cd ..
done

echo "✅ Integration tests completed!"
