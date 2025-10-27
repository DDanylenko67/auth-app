FROM maven:3.9.4-eclipse-temurin-17
WORKDIR /app
COPY target/data-api-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]