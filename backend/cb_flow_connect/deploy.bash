# Build the image
docker build \
  --build-arg DB_URL="jdbc:mysql://34.122.40.90:3306/cb_flow_connect_db" \
  --build-arg DB_USERNAME="cb_flow_dev" \
  --build-arg DB_PASSWORD="j86SsUB9SMSipOF" \
  --build-arg USERNAME="jorge@lugaresvirtuales.com.mx" \
  --build-arg PASSWORD="rkfpkduwvwdrsftp" \
  --build-arg JWT_SECRET_KEY="378973cbbe65a108c49838988091917b" \
  --build-arg JWT_TOKEN_PREFIX="Bearer" \
  --build-arg JWT_TOKEN_EXPIRATION_AFTER_DAYS="1" \
  --build-arg JWT_REFRESH_TOKEN_EXPIRATION_AFTER_DAYS="2" \
  --build-arg JUNO_API_KEY="IXXiZikZVl" \
  --build-arg JUNO_API_KEY_SECRET="9ba0efb1feee01dd4edf909ae26f0c0d" \
  -t us-east1-docker.pkg.dev/life-bot-maikol/cb-flow-connect-api/cb_flow_connect_api:latest .

# Push to Artifact Registry
docker push us-east1-docker.pkg.dev/life-bot-maikol/cb-flow-connect-api/cb_flow_connect_api:latest