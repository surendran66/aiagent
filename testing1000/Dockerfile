FROM eclipse-temurin:17-jre-alpine AS base
WORKDIR /app

# Build stage
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Release image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/testing1000-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
