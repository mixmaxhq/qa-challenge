package teststeps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class MyStepdefs {
    String bodyRes;
    int statusCode;
    Response res;
    RequestSpecification httpRequest;
    @Before
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";
        httpRequest = RestAssured.given().contentType(ContentType.JSON);
    }
    @After
    public void tearDown() {
        RestAssured.reset();
    }

    @Given("I hit the pet-store and I add the Pet details {string},{string},{string},{string},{string},{string},{string},{string}")
    public void iHitThePetStoreAndIAddThePetDetails( String ID, String cID, String cName, String name, String pURL, String tID, String tName, String pStatus) {
        setData(ID, cID,cName, name, pURL, tID, tName,pStatus);
        res = httpRequest.body(bodyRes).post();
    }

    @When("I search for the pet details with Pet-ID {string}")
    public void iSearchForThePetDetailsWithPetID(String pID) {
        res =httpRequest.get(pID);
    }

    @Given("I hit the pet-store and I delete the pet details with Pet-ID {string}")
    public void iHitThePetStoreAndIDeleteThePetDetailsWithPetID(String pID) {
        res =httpRequest.delete(pID);
    }

    @Then("The pet details are checked and the pet profile is {string}")
    public void thePetDetailsAreCheckedAndThePetProfileIs(String statusCode) {
        Assert.assertEquals(res.getStatusCode(),Integer.parseInt(statusCode));
    }
    @And("The details are validated against the added {string} and {string}")
    public void theDetailsAreValidatedAgainstTheAddedAnd(String cName, String pName) {
            JsonPath jsonPathEvaluator = res.jsonPath();
            String categoryName = jsonPathEvaluator.get("category.name");
            Assert.assertEquals(categoryName,cName);
            String dogName = jsonPathEvaluator.get("name");
            Assert.assertEquals(dogName,pName);
    }
    public void setData( String ID, String cID, String cName, String name, String pURL, String tID, String tName,String pStatus)
    {
        bodyRes="{" +
                "  \"id\": "+ID+"," +
                "  \"category\": {" +
                "    \"id\": "+cID+",\n" +
                "    \"name\": \""+cName+"\"\n" +
                "  },\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \""+pURL+"\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": "+tID+",\n" +
                "      \"name\": \""+tName+"\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+pStatus+"\"\n" +
                "}";
    }
    @And("I update the pet status to {string} for Pet {string},{string},{string},{string},{string},{string},{string}")
    public void iUpdateThePetStatusToForPet(String pStatus, String ID, String cID, String cName, String name, String pURL, String tID, String tName) {
        setData(ID, cID,cName, name, pURL, tID, tName,pStatus);
        res = httpRequest.body(bodyRes).put();
    }

    @Then("The status code of the action is {string}")
    public void theStatusCodeOfTheActionIs(String statusCode) {
        Assert.assertEquals(res.getStatusCode(),Integer.parseInt(statusCode));
    }
}

