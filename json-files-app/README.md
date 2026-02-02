# JSON Files Only Application

This is a template application that responds to requests with a JSON object containing file paths and their content.

## Requirements
- Java 17
- Maven 3.9.x

## Configuration
- All configs via environment variables/app props.

## Build


mvn clean package


## Run (local)


export SERVER_PORT=8080
java -jar target/json-files-app.jar


## Docker


docker build -t json-files-app .
docker run -e SERVER_PORT=8080 -p 8080:8080 json-files-app


## Kubernetes
See `k8s/deployment.yaml` for sample deploy.
