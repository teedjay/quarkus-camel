package com.teedjay.quarkus.camel;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class HttpRouteTest {

    @Test
    public void checkLocalHost() {
        given()
            .when().get("/camel/input")
            .then()
                .statusCode(200)
                .body(is("Hello from Thores-MacBook-Proooo.local"));
    }

}
