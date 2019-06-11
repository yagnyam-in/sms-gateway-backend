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

## Test

```bash
curl -X POST -H "Content-Type: application/json" -d@- https://sg.yagnyam.in/api <<-EOF
{
        "phone": "phone number",
        "message": "personal message",
        "appId": "app-id",
        "accessToken": "access-token"
}
EOF

```
