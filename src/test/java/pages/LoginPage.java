package pages;

import base.BasePage;
import base.context.CurrentPageContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.context.DriverContext.*;
import static reporting.ExtentTestManager.getTest;

public class LoginPage extends BasePage {
    final Logger loginPageLogger = LogManager.getLogger(LoginPage.class);

    @FindBy(how = How.ID, using = "user-name")
    public WebElement usernameInput;

    @FindBy(how = How.ID, using = "password")
    public WebElement passwordInput;

    @FindBy(how = How.ID, using = "login-button")
    public WebElement loginButton;

    public synchronized LoginPage setUsername(String username){
        WaitForPageToLoad();
        loginPageLogger.info("Setting User Name: "+username);
        WaitForElementVisible(usernameInput);
        usernameInput.sendKeys(username);
        getTest().info("UserName: "+username);
        return this;
    }

    public synchronized LoginPage setPassword(){
        loginPageLogger.info("Setting Password: secret_sauce");
        WaitForElementVisible(passwordInput);
        passwordInput.sendKeys("secret_sauce");
        getTest().info("Password: secret_sauce");
        return this;
    }

    public synchronized LoginPage clickLogin() {
        loginPageLogger.info("Clicking Login button..");
        WaitForElementClickable(loginButton);
        loginButton.click();
        getTest().info("Clicked Login button..");
        return this;
    }

    public synchronized void loginProcess(String username){
        setUsername(username);
        setPassword();
        clickLogin();
        CurrentPageContext.setCurrentPage(GetInstance(InventoryPage.class));
    }
}
