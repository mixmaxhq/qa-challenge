package pages;

import base.BasePage;
import base.context.CurrentPageContext;
import base.context.LocalDriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.context.DriverContext.*;
import static reporting.ExtentTestManager.getTest;

public class CheckoutStepOnePage extends BasePage {

    final Logger checkoutStepOnePageLogger = LogManager.getLogger(CheckoutStepOnePage.class);

    @FindBy(how = How.ID, using = "first-name")
    public WebElement firstNameInput;
    @FindBy(how = How.ID, using = "last-name")
    public WebElement lastNameInput;
    @FindBy(how = How.ID, using = "postal-code")
    public WebElement postalCodeInput;
    @FindBy(how = How.ID, using = "continue")
    public WebElement continueButton;

    public synchronized Boolean verifyCheckoutStepOnePageUrl() {
        checkoutStepOnePageLogger.info("Getting Checkout Step One page url for verification..");
        return LocalDriverContext.getRemoteWebDriver().getCurrentUrl().contains("checkout-step-one.html") ? true : false;
    }
    public synchronized CheckoutStepOnePage setFirstName(String firstname){
        WaitForPageToLoad();
        checkoutStepOnePageLogger.info("Setting First Name: "+firstname);
        WaitForElementVisible(firstNameInput);
        firstNameInput.sendKeys(firstname);
        getTest().info("First Name: "+firstname);
        return this;
    }

    public synchronized CheckoutStepOnePage setLastName(String lastName){
        checkoutStepOnePageLogger.info("Setting Last Name: "+lastName);
        WaitForElementVisible(lastNameInput);
        lastNameInput.sendKeys(lastName);
        getTest().info("Last Name: "+lastName);
        return this;
    }

    public synchronized CheckoutStepOnePage setPostalCode(String postalCode){
        checkoutStepOnePageLogger.info("Setting Postal Code: "+postalCode);
        WaitForElementVisible(postalCodeInput);
        postalCodeInput.sendKeys(postalCode);
        getTest().info("Postal Code: "+postalCode);
        return this;
    }

    public synchronized CheckoutStepOnePage clickContinue() {
        checkoutStepOnePageLogger.info("Clicking Continue button..");
        WaitForElementClickable(continueButton);
        continueButton.click();
        getTest().info("Clicked Continue button..");
        return this;
    }

    public synchronized CheckoutStepOnePage fillUserInfoForm(String firstName, String lastName,String postalCode) {
        setFirstName(firstName);
        setLastName(lastName);
        setPostalCode(postalCode);
        CurrentPageContext.setCurrentPage(GetInstance(CheckoutStepTwoPage.class));
        clickContinue();
        return this;
    }

}
