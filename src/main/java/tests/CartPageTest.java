package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.CartPageProcedures;
import utils.GlobalVariables;

public class CartPageTest extends BaseTest {
    private CartPageProcedures cartPageProcedures;
    private By checkoutButton = By.id("checkout");
    @BeforeMethod
    public void beforeTest() {
        // Initialize procedures
        cartPageProcedures = new CartPageProcedures(GlobalVariables.driver);
        System.out.println(cartPageProcedures == null);
    }

    

    @Test
    public void clickTest() {
        WebElement checkoutBtn = GlobalVariables.driver.findElement(checkoutButton);
        checkoutBtn.click();
    }
//    @Test
//    public void goToCheckout() {
//        // Proceed to checkout
//    	checkoutButton.click();
//
//        // Add assertions to verify the checkout process was initiated
//    }
}