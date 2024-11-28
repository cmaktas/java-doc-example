# Java Documentation Practices Demo Application
## Overview
This project is a Java Spring Boot application designed to showcase practices in project and code documentation. It includes API documentation using Swagger (OpenAPI) and Javadoc generation. The application provides a practical example of documenting both the application structure and code for better maintainability and readability.

## Features

- **Spring Boot 3.4.0**: A modern, scalable, and production-ready backend framework.
- **Swagger Integration**: Provides interactive API documentation at `/swagger-ui/index.html`.
- **Javadoc Generation**: Auto-generates Javadocs as part of the Maven build process. `/javadocs/index.html`
- **Example Resource Management**: CRUD operations for an "Example" entity with comprehensive documentation.
- **Code-level Documentation**: Every class and method is well-documented with clear explanations of purpose and behavior.
- **JWT-based Authentication**: Implements JWT for API security and integrates with Swagger to showcase SecurityScheme configuration of swagger using `Bearer Authentication`.

## Prerequisites

- **Java 21**
- **Maven 3.6+**

## Setup

### Clone the Repository
```bash
git clone https://github.com/cmaktas/java-doc-example.git
cd java-doc-practices
```

### Build the Application
```bash
mvn clean package
```

### Run the Application
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

## Access the Application
> Swagger UI: http://localhost:8080/swagger-ui/index.html
Hosted Javadocs: http://localhost:8080/javadocs/index.html

## Technologies Used
- Spring Boot 3.4.0
- Spring Security for JWT-based authentication
- SpringDoc OpenAPI for API documentation
- Lombok for boilerplate code reduction
- JJWT for JSON Web Token handling
- Maven Javadoc Plugin for Javadoc generation

## Javadoc Generation
Javadocs are automatically generated during the Maven build process and are accessible at http://localhost:8080/javadocs/index.html.
- Class-level descriptions.
- Method-level documentation with parameters and return values.

## Swagger Integration Overview
The application integrates SpringDoc OpenAPI to provide interactive and comprehensive API documentation available at `/swagger-ui/index.html`. Key highlights include:
- Detailed Metadata
- Bearer Authentication
- Annotated endpoints with:
- DTO-Level Documentation
- Field-specific descriptions
