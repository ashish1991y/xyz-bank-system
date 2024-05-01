# Application Overview

This document outlines our application which is composed of two main business services and five supporting services. The design aims to streamline operations by efficiently managing distinct functionalities.

## System Requirements

Ensure your system has these before starting:

- **Java 17**
- **GIT**
- **MySQL Server**

## Configuration Management

Our application uses a central Git repository for all service configurations called `xyzbank-config-repo`.

### How to Use Configuration Repository
- **Make changes**: Update the configuration files and push them back if you have the necessary permissions. If not, contact the admin (ashish1991y@gmail.com).
- **Local Testing**: Directly edit the `application.yml` in your project to override settings for local testing.

## Business Microservices

### Easy-on-board
Handles new user registrations and logins.
- **POST /register**: Registers a new user.
- **POST /login**: Logs in a user and returns a token.

### Meta Data Service
Maintains essential data like country lists and user roles.
- **GET /country**: Returns a list of countries.

## Supporting Services

### API Gateway
Serves as the front door for all requests, managing routing and security.

### Identity Service
Manages user identity and access based on roles.

### Eureka Discovery Service
Facilitates service discovery and load balancing.

### Authentication Service
Issues JWTs for secure access.
- **POST /token**: Provides a JWT for logged-in users.

### Spring Configuration Server
Manages configurations across services dynamically.

## Database

Uses MySQL for data storage and management.

## Postman Collection

A Postman collection is included for testing the application.

## Swagger UI

Access the Swagger UI to explore the API documentation:
- **URL**: [swagger link](http://localhost:8085/eob/swagger-ui/index.html#/)

## Getting Started

To set up the project:

1. **Set up the database**:
    - Run `xyzBank_db.sql` in MySQL.

2. **Start services in order**:
    - Eureka Discovery Service
    - Configuration Server
    - API Gateway
    - Other Services

3. **Run the Application**: Start all services, then use the application.

4. **Test with Postman**: Import the collection to test endpoints.
