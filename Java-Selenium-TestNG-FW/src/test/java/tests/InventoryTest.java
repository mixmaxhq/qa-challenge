package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BasePage {

    @Test(priority=2,description = "Remove from cart")
    public void verifyRemovingFromCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        verifyaddItemToCart();
        inventoryPage.removeItemFromCart();
        Assert.assertTrue(inventoryPage.isItemRemoved());
    }

    @Test(priority=3,description = "Add to cart")
    public void verifyaddItemToCart() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCart();
        Assert.assertTrue(inventoryPage.isItemAdded());
    }
}
