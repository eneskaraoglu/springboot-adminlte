#!/bin/bash
# deploy-docker.sh: Build and deploy Spring Boot + PostgreSQL with Docker Compose on Ubuntu/Linux

set -e

# Build and start Docker containers (Maven build is handled inside Docker)
sudo docker-compose up --build -d

# Show running containers
sudo docker ps

echo
echo "Deployment complete! Visit http://localhost:8080"
