
k8s commands used

k create deployment players --image=registry.hub.docker.com/vsmuralidhar/player-service:latest --port=8080 --dry-run=client -o yaml >> deployment.yaml
k create namespace develop --dry-run=client -o yaml >> namespace.yaml

k apply -k kustomize


newman run newman/player-service.json --env-var "env=vs-local.minikube.com"