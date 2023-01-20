package tests;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;

import pages.BasePage;
import pages.CheckoutPage;

public class CheckoutTest extends BasePage{
	String fName,lName,zCode;
	public CheckoutTest() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("../Java-Selenium-TestNG-FW/TestData.json"));
		JSONObject jsonObject = (JSONObject) obj;
		 fName  = (String) jsonObject.get("firstName");
		 lName  = (String) jsonObject.get("lastName");
		 zCode  = (String) jsonObject.get("zipCode");
	}

	@Test(priority=0,description = "Checkout E2E")
	public void verifyCheckingOutAnItem() throws InterruptedException {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.navigateToCart();
		checkoutPage.clickCheckOutInfo();
		checkoutPage.enterFirstName(fName);
		checkoutPage.enterLastName(lName);
		checkoutPage.enterZipcode(zCode);
		checkoutPage.isContinueButtonClick();
		Thread.sleep(1000);
		checkoutPage.finishButtonClick();
		Thread.sleep(1000);
		Assert.assertTrue(checkoutPage.isSuccessMessageDisplayed());
	}
}
