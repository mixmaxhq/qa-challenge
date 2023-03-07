package base.api.controllers;

import base.api.helpers.RequestSpec;
import base.api.helpers.ResponseSpec;
import base.api.models.request.UserModelPostRequest;
import base.api.models.response.UserListModelResponse;
import base.api.models.response.UserModelPostResponse;
import base.api.models.response.UserModelPutResponse;
import base.api.models.response.UserModelResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static reporting.ExtentTestManager.getTest;


public class UserController {
    RequestSpec requestSpec;
    ResponseSpec responseSpec;

    public UserController() {
        requestSpec = new RequestSpec();
        responseSpec = new ResponseSpec();
    }

    public UserModelResponse getUser(int id) {
        ValidatableResponse response = given()
                .spec(requestSpec.getRequestSpecification())
                .pathParams("id", id)
                .when()
                .get("/api/users/{id}")
                .then()
                .spec(responseSpec.getResponseSpecification());

        return response.extract()
                .jsonPath()
                .getObject("", UserModelResponse.class);
    }

    public UserListModelResponse getUsersList(int pageId) {
        ValidatableResponse response = given()
                .spec(requestSpec.getRequestSpecification())
                .get("https://reqres.in/api/users?page=" + pageId)
                .then()
                .spec(responseSpec.getResponseSpecification());

        return response.extract()
                .jsonPath()
                .getObject("", UserListModelResponse.class);
    }
    public UserModelPostResponse createUser(UserModelPostRequest user) {

        ValidatableResponse response = given()
                .spec(requestSpec.postRequestSpecification())
                .body(user)
                .post("/api/users")
                .then()
                .spec(responseSpec.postResponseSpecification());

        return response.extract()
                .jsonPath()
                .getObject("", UserModelPostResponse.class);
    }

    public UserModelPutResponse updateUser(int userId,UserModelPostRequest user) {
        ValidatableResponse response = given()
                .spec(requestSpec.postRequestSpecification())
                .pathParams("id", userId)
                .body(user)
                .put("/api/users/{id}")
                .then()
                .spec(responseSpec.putResponseSpecification());

        return response.extract()
                .jsonPath()
                .getObject("", UserModelPutResponse.class);

    }

    public void deleteUser(int id) {
        Response response = given()
                .spec(requestSpec.getRequestSpecification())
                .delete("/api/users/" + id);

        Assert.assertEquals(response.getStatusCode(),204);
        getTest().pass("Validated that User deleted successfully..");
    }

    public void userNotFound() {
        Response response = given()
                .spec(requestSpec.getRequestSpecification())
                .get("/api/users/23");

        Assert.assertEquals(response.getStatusCode(),404);
        getTest().pass("Validated that User Not Found !");
    }
}