package com.moonglow.pages;

import com.moonglow.utilities.ConfigurationReader;
import com.moonglow.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Login_Page {
    public Login_Page(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//a[@href='https://moonglow.md/en/my-account/'])[1]")
    public WebElement login_register_button;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement inputUsername;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[@name='login']")
    public WebElement login_button;

    @FindBy(xpath = "//div[@class='wd-search-form wd-header-search-form']//input[1]")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class = 'wd-header-my-account wd-tools-element wd-event-hover  wd-with-username wd-account-style-icon']//span[2]")
    public WebElement helloIcon;

    @FindBy(xpath = "//div[@class='wd-header-cart wd-tools-element wd-design-5 cart-widget-opener']//a")
    public WebElement cartIcon;

    @FindBy(xpath = "//div[@class='wd-scroll-content']//li")
    public List<WebElement> itemsInCart;

    @FindBy(xpath = "//a[@class='remove remove_from_cart_button']")
    public List<WebElement> removeItemIcons;

    @FindBy(xpath = "//a[@class='button checkout wc-forward']")
    public WebElement checkoutButton;

    /**
     * This method will log in using credentials from
     * configuration.properties
     */
    public void login(){
        login_register_button.click();
        inputUsername.sendKeys(ConfigurationReader.getProperty("username"));
        inputPassword.sendKeys(ConfigurationReader.getProperty("password"));
        login_button.click();
    }
}
