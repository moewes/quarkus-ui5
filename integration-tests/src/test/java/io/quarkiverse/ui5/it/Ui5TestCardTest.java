package io.quarkiverse.ui5.it;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Ui5TestCardTest {

    @Test
    public void testManifestEndpoint() {
        given()
                .when().get("/ui5/cards/manifests/TestCard.json")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCardPageEndpoint() {
        given()
                .when().get("/ui5/cards")
                .then()
                .statusCode(200);
    }
}
