package com.moonglow.step_definition;

import com.moonglow.pages.Login_Page;
import com.moonglow.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Login_stepDefinitions {

    Login_Page loginPage = new Login_Page();

    @When("user enters valid username")
    public void user_enters_valid_username() {

        loginPage.login_register_button.click();
        loginPage.inputUsername.sendKeys(ConfigurationReader.getProperty("username"));
    }
    @When("user enters valid password")
    public void user_enters_valid_password() {
        loginPage.inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        loginPage.login_button.click();
    }

    @Then("verify account icon has Hello username")
    public void verifyAccountIconHasHelloUsername() {

        Assert.assertTrue(loginPage.helloIcon.getText().contains("Hello"));

    }
}
