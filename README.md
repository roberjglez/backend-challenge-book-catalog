# Spring Boot application to manage a books catalog

### Tech stack
* Spring Boot, Maven, Docker, Swagger
* Hexagonal architecture and DDD
* Code language: Java 17
* Testing: Junit5 & Mockito, Cucumber
* Database: Spring Data JPA, PostgreSQL, Flyway

### Some business requirements:

1. It's not possible to have two books with the same ID in database
2. It's possible to modify the publisher of any book, but only if it was published less than 5 years ago

### How to run the microservice:
1. **Build the app**
```
mvn clean install -Dmaven.test.skip=true
```

2. **Run docker-compose to start PostgreSQL and the Spring Boot application**
```
docker-compose up
```

### Where can I see information about the microservice?:

In the following URL: http://localhost:8080/swagger-ui.html


### How to run tests
```
mvn test
```