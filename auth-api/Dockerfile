FROM maven:3.9.4-eclipse-temurin-17
WORKDIR /app
COPY target/auth-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]