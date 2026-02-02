FROM eclipse-temurin:17-jre
ENV JAVA_OPTS=""
WORKDIR /app
COPY target/json-files-app.jar app.jar
COPY sample ./sample
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
