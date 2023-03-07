package pages;

import base.BasePage;
import base.context.CurrentPageContext;
import base.context.LocalDriverContext;
import contants.CartOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

import static base.context.DriverContext.WaitForElementClickable;
import static contants.CartOperation.ADD;
import static reporting.ExtentTestManager.getTest;

public class InventoryPage extends BasePage {
    final Logger inventoryPageLogger = LogManager.getLogger(InventoryPage.class);

    @FindBy(how = How.ID, using = "shopping_cart_container")
    public WebElement goToCartButton;

    @FindBy(how = How.XPATH, using = "//select[@class='product_sort_container']")
    public WebElement selectSortDropdown;

    @FindBy(how = How.XPATH, using = " //div[@class='inventory_item_name']")
    public List<WebElement> inventoryItemListByName;

    @FindBy(how = How.XPATH, using = " //div[@class='inventory_item_price']")
    public List<WebElement> inventoryItemListByPrice;

    public synchronized Boolean verifyInventoryPageUrl() {
        return LocalDriverContext.getRemoteWebDriver().getCurrentUrl().contains("inventory.html") ? true : false;
    }
    public synchronized InventoryPage cartOperationForItemNameDynamically(String itemName, CartOperation cartOperationType){
        inventoryPageLogger.info("Finding item and clicking "+cartOperationType.toString()+" button for cart..");
        String dynamicXpath;
        if(cartOperationType.equals(ADD))
            dynamicXpath = "//button[@id='add-to-cart-"+itemName.toLowerCase().replace(" ","-")+"']";
        else
            dynamicXpath = "//button[@id='remove-"+itemName.toLowerCase().replace(" ","-")+"']";
        WebElement dynamicElement = LocalDriverContext.getRemoteWebDriver().findElement(By.xpath(dynamicXpath));
        WaitForElementClickable(dynamicElement);
        dynamicElement.click();
        getTest().info("Clicked "+cartOperationType.toString()+" button for item: "+itemName);
        CurrentPageContext.setCurrentPage(GetInstance(CartPage.class));
        return this;
    }

    public synchronized InventoryPage clickGotoCart() {
        inventoryPageLogger.info("Clicking Go to cart button..");
        WaitForElementClickable(goToCartButton);
        goToCartButton.click();
        getTest().info("Clicked Go to cart button..");
        return this;
    }

    public synchronized InventoryPage selectSortType(String sortType) {
        inventoryPageLogger.info("Finding sorting select options..");
        Select sortingDropdown = new Select(selectSortDropdown);
        sortingDropdown.selectByVisibleText(sortType);
        getTest().info("Selected sorting type: "+sortType);
        return this;
    }

    public synchronized Boolean verifyInventoryItemListIsSorted(String sortType){
        Iterator<WebElement> iter;
        if(sortType.contains("Name")){
            iter = inventoryItemListByName.iterator();
            String current, previous = iter.next().getText();
            while (iter.hasNext()) {
                current = iter.next().getText();
                if (previous.compareTo(current) > 0) {
                    return false;
                }
                previous = current;
            }
            return true;
        }
        else{
            iter = inventoryItemListByPrice.iterator();
            double current, previous = Double.parseDouble(iter.next().getText().replace("$",""));
            while (iter.hasNext()) {
                current = Double.parseDouble(iter.next().getText().replace("$",""));
                if (previous > current) {
                    return false;
                }
                previous = current;
            }
            return true;
        }
    }
}
