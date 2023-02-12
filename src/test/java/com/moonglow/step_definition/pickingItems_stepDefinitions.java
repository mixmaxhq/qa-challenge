package com.moonglow.step_definition;

import com.moonglow.pages.Login_Page;
import com.moonglow.pages.Search_Page;
import com.moonglow.utilities.BrowserUtils;
import com.moonglow.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class pickingItems_stepDefinitions {

    Login_Page loginPage = new Login_Page();
    Search_Page searchPage = new Search_Page();
    int initialSize = 0;

    //1. User should be able to pick 3 items and add them to the cart
    @Given("user logged in successfully")
    public void user_logged_in_successfully() {
        loginPage.login();
    }

    @When("user writes in the search bar {string}")
    public void user_writes_in_the_search_bar(String item) {
        loginPage.searchBox.sendKeys(item + Keys.ENTER);
    }

    @When("user add them to the cart")
    public void user_add_them_to_the_cart() {
        searchPage.firstItem_AfterSearch.click();
    }

    @Then("verify the item were added to the cart")
    public void verify_the_item_were_added_to_the_cart() {
        loginPage.cartIcon.click();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//li[@class='woocommerce-mini-cart-item mini_cart_item'][1]")));
        Assert.assertTrue(loginPage.itemsInCart.size() != 0);
    }

    //2. User should be able to remove any item from the cart

    @When("user enters the cart")
    public void user_enters_the_cart() {
        loginPage.cartIcon.click();
        initialSize = loginPage.removeItemIcons.size();
    }

    @When("user click the remove button")
    public void user_click_the_remove_button() {
        BrowserUtils.removeRandomItem(loginPage.removeItemIcons);
    }


    @Then("verify the item was removed from the cart")
    public void verify_the_item_was_removed_from_the_cart() {
        int actualSize = loginPage.removeItemIcons.size();
        Assert.assertTrue(actualSize < initialSize);
    }

    // 3. User should be able to proceed to checkout
    @When("user is in the cart")
    public void user_is_in_the_cart() {
        loginPage.cartIcon.click();
    }
    @When("user clicks checkout")
    public void user_clicks_checkout() {
        loginPage.checkoutButton.click();
    }
    @Then("verify user is redirected to checkout page")
    public void verify_user_is_redirected_to_checkout_page() {
        Assert.assertTrue(Driver.getDriver().getTitle().contains("Checkout"));
    }


}
