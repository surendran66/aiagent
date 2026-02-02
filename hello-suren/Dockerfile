FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
EXPOSE 8080
ENV JAVA_OPTS=""
ARG JAR_FILE=target/hello-suren-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
