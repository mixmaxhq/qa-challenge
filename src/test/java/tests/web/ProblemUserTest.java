package tests.web;

import base.Base;
import base.context.CurrentPageContext;
import base.initialization.FrameworkInitialization;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ExcelUtil;

import static reporting.ExtentTestManager.startTest;

public class ProblemUserTest extends FrameworkInitialization {
    @Test(description = "Problem User for validation fail",dataProvider = "getTestData")
    public void problemUserLoginTest(String testName, String description, String userType) {
        startTest(testName,description).assignCategory("ProblemUser");
        CurrentPageContext.setCurrentPage(new Base().GetInstance(LoginPage.class));
        CurrentPageContext.getCurrentPage().As(LoginPage.class).loginProcess(userType);
        Assert.assertFalse(CurrentPageContext.getCurrentPage().As(InventoryPage.class).verifyInventoryPageUrl(), "Verification of Inventory page");
    }

    @DataProvider
    public Object[][] getTestData() throws Throwable {
        Object[][] data = ExcelUtil.getExcelData("testdata.xlsx","ProblemUserTest");
        return data;
    }
}
