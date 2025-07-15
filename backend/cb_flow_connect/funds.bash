#!/bin/bash
# Requires:
# - httpie: https://github.com/jkbrzt/httpie

URL="https://stage.buildwithjuno.com/spei/test/deposits"
API_KEY="" # Your Juno API Key
API_SECRET="" # Your Juno API Secret

SECS=$(date +%s)
DNONCE=$(expr 1000 '*' "$SECS")
HTTPmethod=POST
RequestPath="/spei/test/deposits"
# JSON payload as a single string (no spaces, ensure it's compact)
JSONPayload="{\"amount\":\"100\",\"receiver_clabe\":\"710969000000411444\",\"receiver_name\":\"Acme\",\"sender_name\":\"Acme\",\"sender_clabe\":\"710969000000411444\"}"

# Create the signature
SIGNATURE=$(echo -n $DNONCE$HTTPmethod$RequestPath$JSONPayload | openssl dgst -binary -sha256 -hmac $API_SECRET | xxd -p -c 256)

# Authorization header
AUTH_HEADER="Bitso $API_KEY:$DNONCE:$SIGNATURE"

# Make the POST request
http POST $URL \
  Authorization:"$AUTH_HEADER" \
  Content-Type:"application/json" \
  <<< "$JSONPayload"