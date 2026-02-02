FROM eclipse-temurin:17-jammy AS build
WORKDIR /app
COPY . .
RUN ./mvnw -B package

FROM eclipse-temurin:17-jammy
WORKDIR /app
COPY --from=build /app/target/testing123-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]
