#!/bin/sh

curl -X POST -H "Content-Type: application/json" -d@- https://sg.yagnyam.in/api <<-EOF
{
	"phone": "+911987887891",
	"message": "Hello from the other side",
	"appId": "test",
	"accessToken": "1234567890"
}
EOF
