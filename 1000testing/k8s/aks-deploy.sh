#!/bin/bash
set -e
if [ -z "$DOCKER_IMAGE" ]; then
  echo "Environment variable DOCKER_IMAGE is not set."
  exit 1
fi
kubectl apply -f k8s/deployment.yaml
