package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {
    RequestSpecification petRqSpec;
    ResponseSpecification petResSpec;


    public RequestSpecification PetRqSpec() {

        petRqSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
        return petRqSpec;

    }

    public ResponseSpecification petResponse(){

        petResSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        return petResSpec;

    }
}
