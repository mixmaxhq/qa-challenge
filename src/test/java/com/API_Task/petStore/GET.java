package com.API_Task.petStore;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.API_Task.utilities.PetStoreTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class GET extends PetStoreTestBase {

/*
    Given content type is json
    When user sends a get request to /pet/{id}
    And provides a valid id
    Then status code is 200
    And response type is json
    And response payload values match the following:
            id is provided id
*/

    @Test
    public void testGetRequestWithValidId(){
        int validId = 777;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/" + validId)
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
            .assertThat()
                .body("id", is(validId))
                .log().body();
    }


/*
        Given content type is json
        When user sends a get request to /pet/{id}
        And provides an invalid id
        Then status code is 404
*/

    @Test
    public void testGetRequestWithInvalidId(){
        int invalidId = -1;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/" + invalidId)
        .then()
                .statusCode(404)
                .log().status();
    }
}
