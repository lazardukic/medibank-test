# Audition API

Spring Boot application created with gradle. The purpose of this application is to give users an API in order for them
to be able to fetch auditon post related data.

## How to test

In order to test the application locally please follow these steps:

1 - Build the application with `gradle build`  
2 - Run the application  
3 - Access the generated swagger documentation at `http://localhost:8080/swagger-ui/index.html`  
4 - Test each endpoint through the swagger UI

You can also monitor the health of the application by accessing the health actuator endpoint
at `http://localhost:8080/actuator/health`
