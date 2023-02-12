package com.API_Task.utilities;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class PetStoreTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = ConfigurationReader.getProperty("petURL");
    }

}
