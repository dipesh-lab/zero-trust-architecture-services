package io.s2s.location.rest;

import static io.restassured.RestAssured.given;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.oidc.server.OidcWiremockTestResource;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.smallrye.jwt.build.Jwt;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

@QuarkusTest
@QuarkusTestResource(OidcWiremockTestResource.class)
class CountryResourceIntegrationTest {

    @ConfigProperty(name = "quarkus.oidc.auth-server-url")
    private String keyCloakUrl;

    @Test
    void shouldGetCountries_whenAccessTokenNotProvided_thenThrow401() {
        given()
                .when().get("/countries")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldSearchCountries_whenAccessTokenNotProvided_thenThrow401() {
        given()
                .queryParam("query", "aus")
                .when().get("/countries/search")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldGetCountries_whenClientRoleProvided() {
        System.out.println("Keycloak server " + keyCloakUrl);
        given()
                .header(new Header("Authorization", "Bearer " + getAccessToken(List.of("Location.Basic"))))
                .when().get("/countries")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }

    private String getAccessToken(List<String> appRoles) {
        JsonArrayBuilder roles = Json.createArrayBuilder();
        appRoles.forEach(roles::add);
        JsonObject rolesObj = Json.createObjectBuilder().add("roles", roles).build();
        JsonObject serviceObj = Json.createObjectBuilder().add("location_service", rolesObj).build();


        var token = Jwt.claim("client_id", "location_service")
                .claim("resource_access", serviceObj)
                .issuer(keyCloakUrl)
                .expiresAt(Instant.now().plusSeconds(60))
                .audience("location_service").sign();
        System.out.println("token " + token);
        return token;
    }
}
