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

public class SortingTests extends FrameworkInitialization {
    @Test(description = "Sorting Tests with Data Driven",dataProvider = "getTestData")
    public void sortingTest(String testName, String description, String userType,String sortType) {
        startTest(testName,description).assignCategory("Sorting");
        CurrentPageContext.setCurrentPage(new Base().GetInstance(LoginPage.class));
        CurrentPageContext.getCurrentPage().As(LoginPage.class).loginProcess(userType);
        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(InventoryPage.class).verifyInventoryPageUrl(), "Verification of Inventory page");

        CurrentPageContext.getCurrentPage().As(InventoryPage.class).selectSortType(sortType);
        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(InventoryPage.class).verifyInventoryItemListIsSorted(sortType),"Verification of sorting");
    }

    @DataProvider
    public Object[][] getTestData() throws Throwable {
        Object[][] data = ExcelUtil.getExcelData("testdata.xlsx","SortingTests");
        return data;
    }
}
