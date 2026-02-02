# syntax=docker/dockerfile:1
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
COPY --from=build target/jsontojsontaransform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
