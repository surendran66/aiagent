# Spring Boot Azure Cosmos Example

This project demonstrates a simple Spring Boot application integrated with Azure Cosmos DB using the latest supported libraries.

## Features
- RESTful API with CRUD endpoints (Java 17/Spring Boot 3.2.4)
- Cosmos DB integration (`spring-cloud-azure-starter-data-cosmos`)
- Docker-ready
- Kubernetes deployment YAML
- Environment variables for secrets/configs
- JUnit 5 tests

## Environment Variables
- `COSMOS_URI` - Azure Cosmos DB URI
- `COSMOS_KEY` - Azure Cosmos DB Key
- `COSMOS_DATABASE` - Azure Cosmos DB Database Name

## Running
### Local
- `mvn spring-boot:run`

### Docker
Build: `docker build -t spring-cosmos-app .`
Run: `docker run -e COSMOS_URI=... -e COSMOS_KEY=... -e COSMOS_DATABASE=... -p 8080:8080 spring-cosmos-app`

### Kubernetes
- Edit secrets before deploying `k8s/deployment.yaml`
- `kubectl apply -f k8s/deployment.yaml`
