### Zero Trust Architecture by microservices

Zero trust architecture has become vital for backend services in order to protect them.
Here, I have used Java, Quarkus and KeyClock to protect two microservices. Anyone (internal services or cloud gateway) can communicate them with valid authentication and authorization (by application scopes).


#### KeyClock server configuration

##### Request JWT token for location service

```curl -k -H 'Content-Type: application/x-www-form-urlencoded' -d 'client_id=${LOCATION_SERVICE_CLIENT_ID}' -d 'client_secret=${LOCATION_SERVICE_CLIENT_SECRET}' -d 'grant_type=client_credentials' https://localhost:8443/realms/internal_services/protocol/openid-connect/token```

##### Request JWT token for report service

```curl -k -H 'Content-Type: application/x-www-form-urlencoded' -d 'client_id=${REPORT_SERVICE_CLIENT_ID}' -d 'client_secret=${REPORT_SERVICE_CLIENT_SECRET}' -d 'grant_type=client_credentials' https://localhost:8443/realms/internal_services/protocol/openid-connect/token```


###### Other instruction will be added soon.
