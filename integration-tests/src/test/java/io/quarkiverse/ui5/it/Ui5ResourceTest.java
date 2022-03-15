package io.quarkiverse.ui5.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class Ui5ResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/ui5")
                .then()
                .statusCode(200)
                .body(is("Hello ui5"));
    }
}
