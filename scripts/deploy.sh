#!/bin/bash

# Apply Kubernetes configurations
kubectl apply -f kubernetes/namespaces/
kubectl apply -f kubernetes/configs/
kubectl apply -f kubernetes/secrets/
kubectl apply -f kubernetes/deployments/
kubectl apply -f kubernetes/services/
kubectl apply -f kubernetes/ingress/
kubectl apply -f kubernetes/monitoring/

# Wait for services to be ready
echo "Waiting for services to be ready..."
kubectl wait --for=condition=available --timeout=300s deployment -l app=smop

# Scale services based on environment
if [ "$ENVIRONMENT" == "prod" ]; then
    kubectl scale deployment authentication --replicas=5
    kubectl scale deployment routing --replicas=5
    kubectl scale deployment payment --replicas=3
    kubectl scale deployment notifications --replicas=3
    kubectl scale deployment provider-integration --replicas=3
fi

# Set up auto-scaling
kubectl autoscale deployment authentication --cpu-percent=80 --min=3 --max=10
kubectl autoscale deployment routing --cpu-percent=80 --min=3 --max=10
kubectl autoscale deployment payment --cpu-percent=80 --min=2 --max=5
kubectl autoscale deployment notifications --cpu-percent=80 --min=2 --max=5
kubectl autoscale deployment provider-integration --cpu-percent=80 --min=2 --max=5

echo "Deployment completed successfully!"
