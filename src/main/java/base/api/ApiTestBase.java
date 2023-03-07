package base.api;

import io.restassured.RestAssured;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;


public class ApiTestBase {
    private static final Logger apiTestBaseLogger = LogManager.getLogger(ApiTestBase.class);

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite(ITestContext context) {
        try {
            apiTestBaseLogger.info("Running API Before Suite..");
            RestAssured.baseURI = "https://reqres.in";
        } catch (Throwable t) {
            apiTestBaseLogger.error(ExceptionUtils.getMessage(t));
            Assert.fail("Error occured in API Before Suite !!");
        }
    }

}
