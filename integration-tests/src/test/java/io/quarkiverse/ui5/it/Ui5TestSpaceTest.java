package io.quarkiverse.ui5.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class Ui5TestSpaceTest {

    @Test
    public void testManifestEndpoint() {
        given()
                .when().get("/ui5/spaces/Test?param=Test")
                .then()
                .statusCode(200)
                .body(is("Parameter: Test"));
    }
}
