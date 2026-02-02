# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /workspace/app
COPY pom.xml ./
COPY src ./src
RUN ./mvnw dependency:go-offline || true
RUN apk add --no-cache maven && mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /workspace/app/target/Datattodataransform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
