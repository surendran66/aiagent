#!/bin/bash
set -e
: "${AKS_NAMESPACE:?You must export AKS_NAMESPACE}"
: "${AKS_IMAGE:?You must export AKS_IMAGE}"
: "${AKS_DEPLOYMENT:?You must export AKS_DEPLOYMENT}"

kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml

echo "Deployment to AKS triggered."
