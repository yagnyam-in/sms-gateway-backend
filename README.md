# SMS Gateway Backend

Backend for SMS Gateway

[![Download Android](https://img.shields.io/badge/download-android-blue.svg)](https://dl.sg.yagnyam.in/app)
[![Web Site](https://img.shields.io/badge/web-site-blue.svg)](https://www.sg.yagnyam.in)


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

