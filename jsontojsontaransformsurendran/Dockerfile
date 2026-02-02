FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY pom.xml /build/
COPY src /build/src
WORKDIR /build
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/jsontojsontaransformsurendran-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
