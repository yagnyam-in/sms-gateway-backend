# sms-gateway-backend

Backend for SMS Gateway

## Local Testing

```
./mvnw appengine:run
```

## Deploy

```
gcloud config set project yagnyam-sms-gateway
./mvnw appengine:deploy
```

## Update Maven Wrapper
```
./mvnw -N io.takari:maven:wrapper
```
