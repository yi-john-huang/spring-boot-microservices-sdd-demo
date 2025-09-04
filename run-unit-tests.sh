#!/bin/bash

# Unit tests only execution script

echo "🧪 Running unit tests only..."

services=("auth-service" "user-service" "job-service" "notification-service" "file-storage" "gateway")

for service in "${services[@]}"; do
    echo "📋 Unit testing $service..."
    cd "$service" || exit 1
    mvn clean test -P unit-tests -q
    cd ..
done

echo "✅ Unit tests completed!"
