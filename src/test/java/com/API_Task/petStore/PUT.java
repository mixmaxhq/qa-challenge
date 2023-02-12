package com.API_Task.petStore;

import com.API_Task.POJO.Pet;
import com.API_Task.POJO.StatusEnum;
import com.API_Task.utilities.PetStoreTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PUT extends PetStoreTestBase {

/*
Given I have the payload for creating a new pet
     When I send a POST request to the /pet endpoint with the payload
     Then status code is 200
     And response payload values match the following:
            1. name - Pikachu
            2. status - Sold
 */

    @Test
    public void testUpdatingThePet(){
        Pet dog = new Pet();
        dog.setId(777);
        dog.setName("Pikachu");
        dog.setStatus(StatusEnum.SOLD);

        given()
                .contentType(ContentType.JSON)
                .body(dog)
                .when()
                .put("/pet")
        .then()
                .statusCode(200)
        .assertThat()
                .body("id", is(dog.getId()))
                .body("name", is(dog.getName()))
                .body("status", containsString(dog.getStatus().toString()))
                .log().body();


    }
}
