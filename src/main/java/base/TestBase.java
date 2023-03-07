package base;

import base.context.LocalDriverContext;
import base.enums.BrowserType;
import base.initialization.FrameworkInitialization;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import static base.context.LocalDriverContext.getRemoteWebDriver;
import static base.initialization.FrameworkInitialization.readBrowserConfig;

public class TestBase {
    private static final Logger testBaseLogger = LogManager.getLogger(TestBase.class);
    public static BrowserType BROWSERTYPE = null;
    public static String GRIDURL = null;
    public static String REMOTE = null;
    public static String BASEURL = null;

    public static void initializeConfig(String suiteName,String browser) {
        try {
            Properties configProperties = readBrowserConfig("config");
            BROWSERTYPE = BrowserType.valueOf(browser.toUpperCase());
            GRIDURL = configProperties.getProperty("gridUrl");
            BASEURL = configProperties.getProperty("baseUrl");
            REMOTE = configProperties.getProperty("remote");
            testBaseLogger.info("TestNG XML values are initialized successfully..");
            testBaseLogger.info("GRIDURL: "+GRIDURL);
            testBaseLogger.info("REMOTE: "+REMOTE);
            testBaseLogger.info("BROWSERTYPE: "+BROWSERTYPE);
            testBaseLogger.info("SUITE NAME : " + suiteName);
        }
        catch (Throwable t) {
            testBaseLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occurred while initializing settings.. Parameters: "+ "GridUrl: " + GRIDURL + " Remote: " + REMOTE + " BrowserType: " + BROWSERTYPE);
        }
    }

    @BeforeSuite(alwaysRun = true)
    @Parameters(value = {"browser"})
    public static void beforeSuite(ITestContext context,String browser) {
        try {
            testBaseLogger.info("Running Before Suite.. Setting XML and FrameworkConfig values..");
            initializeConfig(context.getCurrentXmlTest().getSuite().getName(),browser);
            testBaseLogger.info("Before Suite ended successfully...");
        } catch (Throwable t) {
            testBaseLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occured in Before Suite !!");
        }
    }


    @BeforeMethod(alwaysRun = true)
    public static void beforeMethod(Method method){
        try {
            testBaseLogger.info("Running Before Method..");
            LocalDriverContext.setRemoteWebDriverThreadLocal(FrameworkInitialization.InitializeBrowser(BROWSERTYPE));
            getRemoteWebDriver().navigate().to(BASEURL);
            testBaseLogger.info("Driver navigate To URL: "+BASEURL);
            getRemoteWebDriver().manage().window().maximize();
            getRemoteWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30L));
            testBaseLogger.info("Before Method ended successfully...");
        }
        catch (Throwable t){
            testBaseLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occured in Before Method !!");
        }
    }

    @BeforeClass(alwaysRun = true)
    public static void beforeClass() {

    }


    @AfterMethod(alwaysRun = true)
    public static void afterMethod(ITestResult result) {
        testBaseLogger.info("Running After Method.. Preparing reports..");
        try {
            if(getRemoteWebDriver() != null){
                getRemoteWebDriver().quit();
                testBaseLogger.info("Successfully quit Webdriver..");
            }
            testBaseLogger.info("Report is prepared and ready to check results..");
        }
        catch (Throwable t){
            testBaseLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occured in After Method !!");
        }
    }
}