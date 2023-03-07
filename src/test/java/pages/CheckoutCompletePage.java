package pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutCompletePage extends BasePage {

    final Logger checkoutCompletePageLogger = LogManager.getLogger(CheckoutCompletePage.class);

    @FindBy(how = How.XPATH, using = "//h2[@class='complete-header']")
    public WebElement checkoutCompleteHeader;

    @FindBy(how = How.XPATH, using = "//div[@class='complete-text']")
    public WebElement checkoutCompleteText;

    @FindBy(how = How.XPATH, using = "//span[@class='title']")
    public WebElement checkoutCompleteTitle;

    public synchronized String getCheckoutCompleteHeaderText() {
        checkoutCompletePageLogger.info("Getting Checkout complete page header text for verification..");
        return checkoutCompleteHeader.getText();
    }

    public synchronized String getCheckoutCompleteText() {
        checkoutCompletePageLogger.info("Getting Checkout complete page text for verification..");
        return checkoutCompleteText.getText();
    }

    public synchronized String getCheckoutCompleteTitle() {
        checkoutCompletePageLogger.info("Getting Checkout complete page title text for verification..");
        return checkoutCompleteTitle.getText();
    }

}
