package pages;

import base.BasePage;
import base.context.LocalDriverContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static base.context.DriverContext.WaitForElementClickable;
import static reporting.ExtentTestManager.getTest;

public class CheckoutStepTwoPage extends BasePage {
    final Logger checkoutStepTwoPageLogger = LogManager.getLogger(CheckoutStepTwoPage.class);

    @FindBy(how = How.ID, using = "finish")
    public WebElement finishButton;
    @FindBy(how = How.XPATH, using = "//div[@class='summary_subtotal_label']")
    public WebElement summarySubtotalLabel;

    public synchronized Boolean verifyCheckoutStepTwoPageUrl() {
        checkoutStepTwoPageLogger.info("Getting Checkout Step Two page url for verification..");
        return LocalDriverContext.getRemoteWebDriver().getCurrentUrl().contains("checkout-step-two.html") ? true : false;
    }

    public synchronized String getSummarySubTotal(){
        checkoutStepTwoPageLogger.info("Getting summary sub total price..");
        return summarySubtotalLabel.getText().replace("Item total: ","");
    }

    public synchronized CheckoutCompletePage clickFinish() {
        checkoutStepTwoPageLogger.info("Clicking Finish button..");
        WaitForElementClickable(finishButton);
        finishButton.click();
        getTest().info("Clicked Finish button..");
        return GetInstance(CheckoutCompletePage.class);
    }

}
