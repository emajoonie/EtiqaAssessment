# Etiqa Assessment

Welcome to the Interview Assessment repository! This repository contains the code and documentation for the interview assessment.

## About
The Assessment Project is a Spring Boot application designed to showcase my proficiency in building backend applications. It includes features such as CRUD operations for managing customers and products, database integration with MySQL, and API documentation using Swagger.

## Technologies Used
- Java
- Spring Boot
- MySQL
- Spring Data JPA
- Spring Web
- MySQL Connector/J
- Spring Boot DevTools
- Springdoc OpenAPI (Swagger)
- Logback
- Bean Validation API

## Usage

### Accessing the Application:
- Once the project is set up, access the application at [http://localhost:8080](http://localhost:8080).
- Open a web browser and navigate to the provided URL to access the application's user interface.

### Using API Endpoints:
- The application provides RESTful API endpoints for performing CRUD (Create, Read, Update, Delete) operations on resources.
- To interact with the API endpoints, you can use tools like Swagger UI, Postman, or cURL.
- Here are the steps to access the API endpoints using Swagger:
    1. Open a web browser and navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
    2. Swagger UI will display a list of available API endpoints along with their descriptions and input parameters.
    3. Explore the endpoints and interact with them directly through the Swagger UI interface.
    4. You can execute requests such as GET, POST, PUT, DELETE to perform CRUD operations on the resources.

## Setup

### Set up MySQL database:
- Install MySQL server on your machine.
- Create a new database for the project.

### Configuration of application.properties:
1. Navigate to the `src/main/resources` directory in the project.
2. Locate the `application.properties` file.
3. Open the `application.properties` file in a text editor.
4. Configure the database connection settings:
    - Set `spring.datasource.url` to the JDBC URL of your MySQL database.
    - Set `spring.datasource.username` to the username of your MySQL database.
    - Set `spring.datasource.password` to the password of your MySQL database.
5. Save the changes to the `application.properties` file.

