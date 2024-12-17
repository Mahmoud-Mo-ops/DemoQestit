package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.CartPageProcedures;
import utils.BaseTest;
import utils.GlobalVariables;

public class CartPageTest extends BaseTest {
    private CartPageProcedures cartPageProcedures;
    @BeforeMethod
    public void beforeTest() {
        // Initialize procedures
        cartPageProcedures = new CartPageProcedures(GlobalVariables.driver);
    }

    

    @Test(description = "Navigate to checkout page by clicking the checkout button")

    public void clickTest() {
    	cartPageProcedures.NavigateToCheckOutReview();
    }

}