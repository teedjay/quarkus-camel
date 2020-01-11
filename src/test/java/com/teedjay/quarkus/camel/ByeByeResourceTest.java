package com.teedjay.quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ByeByeResourceTest {

    @Test
    public void byebye() {
        given()
            .when().get("/byebye")
            .then()
            .statusCode(200)
            .body(is("Farewell, see you later!"));
    }

}
