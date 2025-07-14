#!/bin/bash
# Requires:
# - httpie: https://github.com/jkbrzt/httpie

URL="https://stage.buildwithjuno.com/mint_platform/v1/clabes"
API_KEY=""             # Your Juno API Key
API_SECRET=""   # Your Juno API Secret

SECS=$(date +%s)
DNONCE=$(expr 1000 '*' "$SECS")
HTTPmethod=POST
RequestPath="/mint_platform/v1/clabes"
# JSON payload as a single string (no spaces, ensure it's compact)
JSONPayload='{}'

# Create the signature
SIGNATURE=$(echo -n $DNONCE$HTTPmethod$RequestPath$JSONPayload | openssl dgst -binary -sha256 -hmac $API_SECRET | xxd -p -c 256)

# Authorization header
AUTH_HEADER="Bitso $API_KEY:$DNONCE:$SIGNATURE"

# Make the POST request
http POST $URL \
  Authorization:"$AUTH_HEADER" \
  Content-Type:"application/json" \
  <<< "$JSONPayload"