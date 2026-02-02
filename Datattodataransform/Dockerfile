FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw -B package --no-transfer-progress -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
ENV COSMOS_DB_URI="https://raglearning1.documents.azure.com:443/"
ENV COSMOS_DB_KEY="dummy"
COPY --from=build /app/target/Datattodataransform*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]
