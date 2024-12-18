package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.CartPageProcedures;
import utils.BaseTest;
import utils.GlobalVariables;

public class CartPageTest extends BaseTest {
    private CartPageProcedures cartPageProcedures;
    @BeforeMethod(description = "Initializes CartPageProcedures before each test method for cart-related operations.")
    public void beforeTest() {
        // Initialize procedures
        cartPageProcedures = new CartPageProcedures(GlobalVariables.getDriver());
    }

    

    @Test(description = "Navigate to checkout page by clicking the checkout button")
    public void clickTest() {
    	cartPageProcedures.NavigateToCheckOutReview();
    }

}