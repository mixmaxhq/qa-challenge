package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    By removeOption = By.xpath("//button[contains(text(), 'Remove')]");
    By addProductToCart = By.xpath("//button[contains(text(), 'Add to cart')]");
    By productDetails = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    WebDriver driver;
    WebDriverWait wait;
    public InventoryPage(WebDriver driver) {
        this.driver=driver;
        wait = new WebDriverWait(driver, 5);
    }
    public void removeItemFromCart() {
        driver.findElement(removeOption).click();
    }
    public boolean isItemRemoved() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(productDetails)).booleanValue();
    }
    public void addItemToCart() {
        driver.findElement(addProductToCart).click();
    }

    public boolean isItemAdded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productDetails)).isDisplayed();
    }
}
