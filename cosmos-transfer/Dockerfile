# ------------ Build Stage ------------
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY pom.xml mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B
COPY src src
RUN ./mvnw package -DskipTests -B
# ------------ Run Stage ------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/cosmos-transfer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
