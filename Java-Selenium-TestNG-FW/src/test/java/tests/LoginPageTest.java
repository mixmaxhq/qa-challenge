package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.BasePage;

public class LoginPageTest extends BasePage{

	//* Test invalid login attempts using data from DataProvider
	@DataProvider(name="accounts")
	public Object[][] getData(){
		return new Object[][] {
			{"locked_out_user", "secret_sauce"}, //locked out user
			{"incorrectuser", "secret_sauce"} //Invalid user
		};
	}

	@Test(priority=0, dataProvider="accounts",description = "Validate login_negative")
	public void verifyInvalidLoginCredentials(String testUsername, String testPassword) {
		LoginPage login = new LoginPage(driver);
		login.typeUsername(testUsername);
		login.typePassword(testPassword);
		login.clickSignIn();
		Assert.assertTrue(login.errorContainerDisplayed());
	}
	
	@Test(priority=0,description = "login_Positive")
	@Parameters({"username", "password","inventoryPageURL"})
	public void verifyLogin(String username, String password,String inventoryPageURL) {
		LoginPage login = new LoginPage(driver);
		login.typeUsername(username);
		login.typePassword(password);
		login.clickSignIn();
		Assert.assertTrue(login.verifyNewPage(inventoryPageURL));
	}

}	
