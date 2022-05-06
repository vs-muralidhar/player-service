# Player Service

Simple Spring Boot REST Application that provides the basic CRUD services. Purpose of this project is to integrate the different tech stack.

## Tech

- spring boot
- junit-5
- maven & gradle
- jib plugin
- h2 database
- kustomize
- newman

## Jib Plugin

```sh
mvn compile jib:build
```
```sh
gradle jib
```

## Kustomize

```sh
kubectl apply -k kustomize
```

## Newman

```sh
newman run newman/player-service.json --env-var "env=vs-local.minikube.com"
```

[//]: # (k create deployment players --image=registry.hub.docker.com/vsmuralidhar/player-service:latest --port=8080 --dry-run=client -o yaml >> deployment.yaml)
[//]: # (k create namespace develop --dry-run=client -o yaml >> namespace.yaml)


