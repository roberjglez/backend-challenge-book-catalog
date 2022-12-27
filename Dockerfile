FROM openjdk:17.0.1-jdk-slim
EXPOSE 8080
ADD target/backend-challenge-books-catalog-0.0.1-SNAPSHOT.jar backend-challenge-books-catalog-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "backend-challenge-books-catalog-0.0.1-SNAPSHOT.jar"]
