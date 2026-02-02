FROM eclipse-temurin:17-jre as runtime
ARG JAR_FILE=target/fivetesting-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
