FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/health-api-1.0.0.jar app.jar
EXPOSE 8080
ENV SERVER_PORT=8080
ENTRYPOINT ["java", "-jar", "app.jar"]
