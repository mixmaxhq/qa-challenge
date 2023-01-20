package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

	By checkOutButton = By.name("checkout");
	By firstNameField = By.name("firstName");
	By lastNameField = By.name("lastName");
	By zipcodeField = By.name("postalCode");
	By continueToPaymentButton = By.name("continue");
	By clickOnCart = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
	By finishButton = By.name("finish");

	By successmsg = By.id("checkout_complete_container");
	WebDriver driver;
	WebDriverWait wait;
	
	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	public void enterFirstName(String firstName) {
		driver.findElement(firstNameField).clear();
		driver.findElement(firstNameField).sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		driver.findElement(lastNameField).clear();
		driver.findElement(lastNameField).sendKeys(lastName);
	}

	public void enterZipcode(String zipcode) {
		driver.findElement(zipcodeField).clear();
		driver.findElement(zipcodeField).sendKeys(zipcode);
	}

	public void isContinueButtonClick() {
		driver.findElement(continueToPaymentButton).click();
	}
	public void clickCheckOutInfo() {
		driver.findElement(checkOutButton).click();
	}

	public void finishButtonClick() {
		driver.findElement(finishButton).click();
	}
	public void navigateToCart() {
		driver.findElement(clickOnCart).click();
	}
	public boolean isSuccessMessageDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successmsg)).isDisplayed();
	}
	
}
