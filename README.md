# Spring Boot AdminLTE Integration

This project is a web-based software solution developed using Spring Boot and Thymeleaf, featuring AdminLTE v3 for the frontend UI. It includes JWT-based security and is containerized using Docker.

## Features

- Admin Dashboard with custom content
- AdminLTE Calendar integration
- User Management (CRUD operations)
- Full AdminLTE v3 Demo Components
- JWT-based authentication
- PostgreSQL database
- Docker containerization

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL (if running locally)

## Quick Start

1. Clone the repository
2. Start the application using Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. Access the application at http://localhost:8080

## Development

To run the application locally:

1. Start PostgreSQL:
   ```bash
   docker-compose up -d postgres
   ```

2. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Security

The application uses JWT (JSON Web Tokens) for authentication. Default credentials:

- Username: admin
- Password: admin123

## License

This project is licensed under the MIT License.
