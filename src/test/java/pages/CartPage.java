package pages;

import base.BasePage;
import base.context.CurrentPageContext;
import base.context.LocalDriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.context.DriverContext.WaitForElementClickable;
import static reporting.ExtentTestManager.getTest;

public class CartPage extends BasePage {
    final Logger cartPageLogger = LogManager.getLogger(CartPage.class);

    @FindBy(how = How.XPATH, using = "//div[@class='inventory_item_price']")
    public WebElement itemPriceInCart;

    @FindBy(how = How.ID, using = "checkout")
    public WebElement checkoutButton;

    public synchronized Boolean verifyCartPageUrl() {
        return LocalDriverContext.getRemoteWebDriver().getCurrentUrl().contains("cart.html") ? true : false;
    }

    public synchronized String getCartItemPrice(){
        cartPageLogger.info("Getting text of cart item price..");
        return itemPriceInCart.getText();
    }

    public synchronized CartPage clickCheckout() {
        cartPageLogger.info("Clicking Checkout button..");
        WaitForElementClickable(checkoutButton);
        checkoutButton.click();
        getTest().info("Clicked Checkout button..");
        CurrentPageContext.setCurrentPage(GetInstance(CheckoutStepOnePage.class));
        return this;
    }
}
