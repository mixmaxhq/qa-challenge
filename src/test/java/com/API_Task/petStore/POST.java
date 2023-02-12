package com.API_Task.petStore;

import com.API_Task.POJO.Pet;
import com.API_Task.POJO.StatusEnum;
import com.API_Task.utilities.PetStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POST extends PetStoreTestBase {

    /*
     Given I have the payload for creating a new pet
     When I send a POST request to the /pet endpoint with the payload
     Then status code is 200
     And response payload values match the following:
            1. name - Fluffy
            2. status - available

     */

    @Test
    public void testCreatingPet(){

        Pet dog = new Pet();
        dog.setName("Fluffy");
        dog.setId(777);
        dog.setStatus(StatusEnum.AVAILABLE);

        given()
                .contentType(ContentType.JSON)
                .body(dog)
                .when()
                .post("/pet")
        .then()
                .statusCode(200)
                .assertThat()
                .body("name", is(dog.getName()))
                .body("status", containsString(dog.getStatus().toString()))
                .body("id", is(dog.getId()))
                .log().body();

    }

    @Test
    public void testNegativePostPet() {
        Pet cat = new Pet();
        cat.setStatus(StatusEnum.AVAILABLE);
        cat.setId(-7859);
        cat.setName("");
        cat.setStatus(StatusEnum.PENDING);

        given()
                .contentType(ContentType.JSON)
                .body(cat)
                .post("/pet")
        .then()
                 .statusCode(405)
                 .log().body();


    }


}
