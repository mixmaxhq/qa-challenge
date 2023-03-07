package tests.api;

import base.api.ApiTestBase;
import base.api.controllers.UserController;
import base.api.models.request.UserModelPostRequest;
import base.api.models.response.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;
import static reporting.ExtentTestManager.getTest;
import static reporting.ExtentTestManager.startTest;

public class ReqresTests extends ApiTestBase{
    UserController userController = new UserController();
    int userId;

    @Test (priority = 1)
    public void printUsersWithOddIdNumber() {
        startTest("GetUsersFilterOddIdNumbers","Get All Users and Print by Odd ID numbers").assignCategory("Get");
        int count = 1;
        getTest().info("Printing all odd id number user data: ");
        while(true){
            UserListModelResponse response = userController
                    .getUsersList(count);
            int totalPageCount = response.getTotalPages();
            List<UserData> oddUserIdList = (Arrays.stream(response.getData()).filter(id -> id.getId() % 2 == 1).collect(Collectors.toList()));
            for (UserData userData : oddUserIdList){
                getTest().info(userData.toString());
            }
            if(count < totalPageCount){
                count++;
            }
            else{
                break;
            }
        }
    }

    @Test(priority = 2)
    public void createUser() {
        startTest("Create User","Create User").assignCategory("Post");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        UserModelPostRequest user = new UserModelPostRequest("Test", "test");

        UserModelPostResponse responseUserController =
                userController
                        .createUser(user);
        userId = responseUserController.getId();
        getTest().info("Validating user creation date is today !");
        getTest().info("User Creation Date: "+sdf.format(responseUserController.getCreatedAt().getTime()));
        getTest().info("Today: "+ sdf.format(new Date()));
        Assert.assertEquals(sdf.format(responseUserController.getCreatedAt().getTime()), sdf.format(new Date()));
    }

    @Test(priority = 3)
    public void updateUser() {
        startTest("Update User","Update User").assignCategory("Put");
        UserModelPostRequest user = new UserModelPostRequest("Kashmir", "Engineer");
        getTest().info("Updating user info..");
        UserModelPutResponse responseUserController =
                userController
                        .updateUser(userId,user);
        getTest().info("Update Date: "+responseUserController.getUpdatedAt());
        getTest().info("Updated Name: "+responseUserController.getName());
        getTest().info("Updated Job: "+responseUserController.getJob());
    }

    @Test(priority = 4,dataProvider = "delayValues")
    public void delayUser(int delayValue) {
        startTest("GetListOfUsersWithDelay","Get a list of users with delay value").assignCategory("Delay");
        getTest().info("Get the list of users with delay query parameter: "+delayValue);
        // get the list of users with delay query parameter
        Response response = RestAssured.given().when().get("https://reqres.in/api/users?delay=" +delayValue);

        // validate the response time is no longer than 1 second
        long responseTime = response.time();
        getTest().info("Response Time: "+responseTime+"ms for Delay Value: "+delayValue);
        assertTrue(responseTime <= 1000, "Response time is longer than 1 second: " + responseTime + "ms");
    }


    @Test (priority = 5)
    public void asynchronous() {
        startTest("GetUsersAsynchronous","Asynchronous Get Users").assignCategory("Asynchronous");
        // create an array of 10 CompletableFutures for each user request
        CompletableFuture<UserModelResponse>[] futures = new CompletableFuture[10];
        getTest().info("Getting 10 single users asynchronous..");
        for (int i = 1; i <= 10; i++) {
            // create a CompletableFuture for each user request
            int finalI = i;
            CompletableFuture<UserModelResponse> future = CompletableFuture.supplyAsync(() ->
                    userController.getUser(finalI)
            );
            futures[i-1] = future; // add the future to the array
        }
        getTest().info("Waiting for all the futures to complete...");
        // wait for all the futures to complete
        CompletableFuture.allOf(futures).join();

        // get the responses from the futures
        getTest().info("Printing responses from the futures..");
        for (int i = 0; i < 10; i++) {
            UserModelResponse response = futures[i].join();
            getTest().info(response.toString());
        }
    }

    @Test (priority = 6)
    public void deleteUser() {
        startTest("DeleteUser","Delete User").assignCategory("Delete");
        getTest().info("Deleting user id: 2");
        userController.deleteUser(2);
    }

    @Test(priority = 7)
    public void getUserNotFound() {
        startTest("Try to get Invalid User Data","Invalid User Info").assignCategory("NotFound-404");
        getTest().info("Trying to get user id: 23");
        userController.userNotFound();
    }

    @Test(priority = 8)
    public void postUserWithWrongBody() {
        startTest("Create User - Negative Test","Create User with Invalid Body Data - Negative Testing").assignCategory("Post");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        UserModelPostRequest user = new UserModelPostRequest("^^%&/?=&-", "1234567");

        UserModelPostResponse responseUserController =
                userController
                        .createUser(user);
        userId = responseUserController.getId();
        getTest().info("Validating user creation date is today !");
        getTest().info("User Creation Date: "+sdf.format(responseUserController.getCreatedAt().getTime()));
        getTest().info("Today: "+ sdf.format(new Date()));
        Assert.assertEquals(sdf.format(responseUserController.getCreatedAt().getTime()), sdf.format(new Date()));
    }


    @DataProvider(name = "delayValues")
    public Object[][] delayValues() {
        return new Object[][]{{0}, {3}};
    }
}

