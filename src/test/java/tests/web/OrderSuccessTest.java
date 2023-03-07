package tests.web;

import base.Base;
import base.context.CurrentPageContext;
import base.initialization.FrameworkInitialization;
import contants.CartOperation;
import contants.ConstantData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.ExcelUtil;

import static reporting.ExtentTestManager.startTest;

public class OrderSuccessTest extends FrameworkInitialization {
    @Test(description = "Order Successfully Test",dataProvider = "getTestData")
    public void OrderTest(String testName, String description, String userType, String firstItemName,String secondItemName) {
        startTest(testName,description).assignCategory("Order-Checkout");

        CurrentPageContext.setCurrentPage(new Base().GetInstance(LoginPage.class));
        CurrentPageContext.getCurrentPage().As(LoginPage.class).loginProcess(userType);
        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(InventoryPage.class).verifyInventoryPageUrl(), "Verification of Login process");
        CurrentPageContext.getCurrentPage().As(InventoryPage.class).cartOperationForItemNameDynamically(firstItemName, CartOperation.ADD)
                .cartOperationForItemNameDynamically(secondItemName, CartOperation.ADD)
                .clickGotoCart()
                .cartOperationForItemNameDynamically(secondItemName, CartOperation.REMOVE);

        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(CartPage.class).verifyCartPageUrl(), "Verification of Cart Page");
        String cartItemPrice = CurrentPageContext.getCurrentPage().As(CartPage.class).getCartItemPrice();
        CurrentPageContext.getCurrentPage().As(CartPage.class).clickCheckout();

        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(CheckoutStepOnePage.class).verifyCheckoutStepOnePageUrl(), "Verification of Checkout Step One Page");

        CurrentPageContext.getCurrentPage().As(CheckoutStepOnePage.class).fillUserInfoForm(
                ConstantData.FIRST_NAME,
                ConstantData.LAST_NAME,
                ConstantData.POSTAL_CODE);

        Assert.assertTrue(CurrentPageContext.getCurrentPage().As(CheckoutStepTwoPage.class).verifyCheckoutStepTwoPageUrl(), "Verification of Checkout Step Two Page");
        String summarySubtotal = CurrentPageContext.getCurrentPage().As(CheckoutStepTwoPage.class).getSummarySubTotal();

        Assert.assertEquals(cartItemPrice,summarySubtotal,"Item price on cart verified with Summary Subtotal price");
        CheckoutCompletePage checkoutCompletePage = CurrentPageContext.getCurrentPage().As(CheckoutStepTwoPage.class).clickFinish();

        Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteHeaderText(),
                ConstantData.CHECKOUT_PAGE_HEADER, "Verification of Order Info Header");

        Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteTitle(),
                ConstantData.CHECKOUT_PAGE_TITLE, "Verification of Order-Checkout Title");

        Assert.assertEquals(checkoutCompletePage.getCheckoutCompleteText(),
                ConstantData.CHECKOUT_PAGE_MESSAGE, "Verification of Order Complete Message");
        }
    @DataProvider
    public Object[][] getTestData() throws Throwable {
        Object[][] data = ExcelUtil.getExcelData("testdata.xlsx","FirstValidationTestData");
        return data;
    }
}
