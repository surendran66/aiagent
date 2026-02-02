FROM eclipse-temurin:17-jre AS runtime
COPY target/hello-world-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
