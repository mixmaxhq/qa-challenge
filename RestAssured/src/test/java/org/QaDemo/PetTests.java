package org.QaDemo;

import config.DataBuilder;
import config.PetEndpoints;
import config.TestConfig;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Pet;


import static io.restassured.RestAssured.*;

public class PetTests extends TestConfig {

    private Pet petPayload;
    //pet object variable that can be referenced across all tests

    @Test(priority = 1)
    public void addPet() {
        //Create a new pet, add the pet using the POST Pet endpoint
        DataBuilder dataBuilder = new DataBuilder();
        //Create a new instance of DataBuilder
        petPayload = dataBuilder.buildPet();
        //Build a new pet using the buildPet method in DataBuilder.  Pet is stored in the petPayload variable to be referenced later.

               given()
                    .spec(PetRqSpec())
                    .body(petPayload)
               .when()
                    .post(PetEndpoints.PET)
               .then()
                    .spec(petResponse());

    }


    @Test(priority = 2, dependsOnMethods = {"addPet"})
    public void getPet() {
        //Use GET endpoint to get the Pet created in previous test.  Confirm that response matches the json schema.
        //The response is stored in a Pet object variable called res so assertions can be made against it.

        Pet res = given()
                    .spec(PetRqSpec())
                    .pathParams("petID", petPayload.getId())
                .when()
                    .get(PetEndpoints.PET_BY_ID)
                .then()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetPetByIdJsonSchema.json"))
                    .extract().response().as(Pet.class);

        int resID = res.getId();
        String resName = res.getName();

        Assert.assertEquals(resName, petPayload.getName());
        Assert.assertEquals(resID, petPayload.getId());
        //Assert that the Pet name and ID in the GET response matches PetPayload

    }


    @Test(priority = 3, dependsOnMethods = {"addPet"})
    public void deletePet() {
        //Delete the pet created in the previous test using the DELETE Pet endpoint
                given()
                    .spec(PetRqSpec())
                    .pathParams("petID", petPayload.getId())
                .when()
                    .delete(PetEndpoints.PET_BY_ID)
                .then()
                    .spec(petResponse());

    }


    @Test(priority = 4, dependsOnMethods = {"deletePet"})
    public void getDeletedPet() {
        //Confirm the pet was deleted in the previous test by attempting to GET the deleted pet.
                given()
                    .spec(PetRqSpec())
                    .pathParams("petID", petPayload.getId())
                .when()
                    .get(PetEndpoints.PET_BY_ID)
                .then()
                    .statusCode(404);
                    // Expect 404 since pet no longer exists
    }



}
