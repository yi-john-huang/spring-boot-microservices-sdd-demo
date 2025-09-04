# Product Overview

This is a Spring Boot microservices-based job marketplace platform that enables users to post job advertisements and make offers on available jobs.

## Core Features

- **User Management**: Registration, authentication, and profile management with role-based access (ADMIN/USER)
- **Job Marketplace**: Categories, job postings, advertisements, and offer management
- **File Storage**: Upload and download functionality for images and documents
- **Notifications**: Real-time notifications via Kafka messaging
- **Security**: JWT-based authentication and authorization throughout all services

## Business Domain

The platform operates as a freelance/job marketplace where:
- Users can register as either employees or employers
- Admins manage categories and job types
- Users create advertisements for services they offer
- Other users can make offers on these advertisements
- The system handles notifications for offer updates and job matches

## API Gateway

All client requests go through the API Gateway at `localhost:8080` with service routing via `/v1/{service-name}/` pattern.