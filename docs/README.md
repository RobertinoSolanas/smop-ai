# SMOP Platform Deployment and Testing Guide

## Prerequisites
- Docker and Docker Compose installed
- Kubernetes cluster (Minikube or cloud provider)
- Terraform installed
- kubectl installed

## Deployment Steps

1. **Infrastructure Setup**
```bash
terraform init
terraform apply
```

2. **Build Docker Images**
```bash
mvn clean package -DskipTests
docker-compose build
```

3. **Deploy to Kubernetes**
```bash
./scripts/deploy.sh
```

4. **Verify Deployment**
```bash
kubectl get pods
kubectl get services
```

## Running End-to-End Tests

1. **Run Integration Tests**
```bash
mvn test -pl integration-tests
```

2. **Manual Testing Workflow**
- Register a new user at POST /api/auth/register
- Login at POST /api/auth/login
- Plan a route at GET /api/routing/route
- Process payment at POST /api/payments
- Track ride status via WebSocket at /ws

## Monitoring and Logging

Access monitoring tools:
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000
- Kibana: http://localhost:5601

## Troubleshooting

Common issues:
- Service not starting: Check logs with `kubectl logs <pod-name>`
- Connection issues: Verify service ports and network policies
- Authentication failures: Check JWT configuration
