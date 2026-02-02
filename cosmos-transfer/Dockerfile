FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Make sure the JAR name matches your pom.xml artifactId
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
