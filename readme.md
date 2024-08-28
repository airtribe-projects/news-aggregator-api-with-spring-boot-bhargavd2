# News API Application

## Overview

This Spring Boot application provides a basic implementation for 
user registration, login, and news preferences management. 
It uses JWT for token-based authentication and integrates with 
external news API (newsdata.io) to fetch and manage news articles based on user preferences.

## Features

- **User Registration and Login**
- **JWT Authentication**
- **Manage User Preferences**
- **Fetch News Based on Preferences**
- **Search News by Keywords**

## Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- In-memory Database (H2)
- Spring WebClient or RestTemplate
- Validation with Spring
- External News API (newsdata.io)

## Setup Instructions

### Prerequisites

- Java 17 or later
- Maven or Gradle
- An IDE such as IntelliJ IDEA or Eclipse
- API keys for external news services (sign up to https://newsdata.io/register)

### Project Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/airtribe-projects/news-aggregator-api-with-spring-boot-bhargavd2.git
   cd news-aggregator-api-with-spring-boot-bhargavd2

2. **Build the Project**

    ```bash
   mvn clean install

3. **Configure API Keys**

create a .env file and add

    API_KEY=<<Key>>

4. Run the Application

   Run `NewsAggregatorApplication` class 

## API Endpoints

### AuthenticationController

1. **Register a New User**
    - URL: POST /api/register
    - Request Body
   ```bash
   curl --location 'localhost:8080/api/register' \
   --header 'Content-Type: application/json' \
   --data '{
      "username":"test",
      "password": "test123"
   }'
   
2. **Login a User**
   - URL: POST /api/login
   - Request Body
   ```bash
   curl --location 'localhost:8080/api/login' \
   --header 'Content-Type: application/json' \
   --data '{
     "username":"hi",
     "password": "test123"
   }'
    
### PreferencesController

1. **Retrieve Preferences**
   - URL: GET /api/preferences
   - Headers: Authorization: Bearer <token>
   ```bash
   curl --location --request GET 'localhost:8080/api/preferences' \
   --header 'Content-Type: application/json' \
   --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ.eyJzdWIiOiJoaSIsImlhdCI6MTcyNDY3ODU3NywiZXhwIjoxNzI0NjgyMTc3fQ.K8UelGz3BZu14boLs5GLt-_3s2R3c5awOh5niZgZwPo' \

2. **Login a User**
   - URL: PUT /api/preferences
   - Request Body
   ```bash
   curl --location --request PUT 'localhost:8080/api/preferences' \
   --header 'Content-Type: application/json' \
   --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ.eyJzdWIiOiJoaSIsImlhdCI6MTcyNDY3ODU3NywiZXhwIjoxNzI0NjgyMTc3fQ.K8UelGz3BZu14boLs5GLt-_3s2R3c5awOh5niZgZwPo' \
   --data '["sports"]'

### NewsController

1. **Fetch News**
   - URL: GET /api/news
   - Headers: Authorization: Bearer <token>
   ```bash
   curl --location 'localhost:8080/api/news/' \
   --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ.eyJzdWIiOiJoaSIsImlhdCI6MTcyNDY3ODU3NywiZXhwIjoxNzI0NjgyMTc3fQ.K8UelGz3BZu14boLs5GLt-_3s2R3c5awOh5niZgZwPo'

## Exception Handling

The application includes proper exception handling for:

 - **DuplicateUsernameException** : when user tries to register with username which already exits
 - **ExternalApiException** : when we have issue with external API call
 - **InvalidCredentialsException** : when user tries to log in's with bad Credentials
 - **InvalidJwtAuthenticationException** : whem user make a request with bad JWT token
 - **InvalidRequestException** : then request from user is invalid

## Future Plans
 - **Caching Mechanism**: Implement caching to store news articles and reduce the number of calls to external APIs.
 - **Read and Favorite** Status: Mark articles as read or favorite, and retrieve them based on their status.
 - **Periodic Updates**: Implement a background mechanism to periodically update cached news articles.