# --------- Multi-stage build ---------
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/datatransform-1.0.0.jar app.jar
ENV COSMOS_ENDPOINT=https://raglearning1.documents.azure.com:443/
ENV COSMOS_KEY=dummy
ENV COSMOS_DATABASE=n8n
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
