# Jsontransform

Spring Boot service to transfer and transform data from one Cosmos DB collection to another.

## Building
bash
./mvnw clean package


## Running
bash
export COSMOS_URI=https://raglearning1.documents.azure.com:443/
export COSMOS_KEY=dummy
java -jar target/Jsontransform-0.0.1-SNAPSHOT.jar


## Docker
Build:
bash
docker build -t cosmos-transfer .

Run:
bash
docker run -e COSMOS_URI -e COSMOS_KEY -p 8080:8080 cosmos-transfer


## Kubernetes
Apply with:
bash
kubectl apply -f k8s/deployment.yaml -f k8s/service.yaml


## Test
bash
./mvnw test

