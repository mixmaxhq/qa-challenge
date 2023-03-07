package base.context;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.ExtentTestManager;

import java.time.Duration;

public class DriverContext {
    final static Logger driverMethodsLogger = LogManager.getLogger(DriverContext.class);
    static long timeOutInSeconds = 30L;

    public static synchronized void WaitForPageToLoad() {
        try {
            WebDriverWait wait= new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(30));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getRemoteWebDriver();

            ExpectedCondition<Boolean> jsLoad = webDriver -> (jsExecutor).executeScript("return document.readyState").toString().equals("complete");

            //Get JS Ready
            boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

            if(!jsReady)
                wait.until(jsLoad);

            driverMethodsLogger.info("Page is ready..");
            ExtentTestManager.getTest().info("Page is ready...");
        } catch (Throwable t) {
            driverMethodsLogger.error(ExceptionUtils.getMessage(t));
            ExtentTestManager.getTest().fail("Error occured in DriverContext waitForPageToLoad !!");
        }
    }

    public static synchronized void WaitForElementVisible(WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(timeOutInSeconds))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(timeOutInSeconds)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        } catch (Throwable t) {
            driverMethodsLogger.error(ExceptionUtils.getMessage(t));
            ExtentTestManager.getTest().fail("Element: " + elementFindBy + " WebElement is not visible !!");
        }
    }

    public static synchronized void WaitForElementClickable(final WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(timeOutInSeconds))
                    .pollingEvery(Duration.ofSeconds(5))
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
        } catch (Throwable t) {
            driverMethodsLogger.error(ExceptionUtils.getMessage(t));
            ExtentTestManager.getTest().fail("Element: " + elementFindBy + " WebElement is not clickable !!");
        }
    }
}
