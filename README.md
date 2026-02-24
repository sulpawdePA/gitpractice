# Centralized Test Report App

Minimal Spring Boot service that accepts test report payloads and stores them in PostgreSQL. Designed to run in OpenShift (OCP).

Quick start (local, requires Java and Maven):

Build:
```bash
mvn package -DskipTests
```

Run locally (requires a running Postgres):
```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/reportdb \
SPRING_DATASOURCE_USERNAME=reportuser \
SPRING_DATASOURCE_PASSWORD=reportpass \
java -jar target/report-app-0.1.0.jar
```

Build Docker image and push to registry:
```bash
docker build -t <your-registry>/report-app:latest .
docker push <your-registry>/report-app:latest
```

Deploy to OpenShift (example):
```bash
# create secret with DB creds
oc create secret generic report-db-secret --from-literal=username=reportuser --from-literal=password=reportpass
oc apply -f k8s/openshift-deployment.yaml
```

API:
- `POST /api/reports` — create a report (JSON body)
- `GET /api/reports` — list all reports
