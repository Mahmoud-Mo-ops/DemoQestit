package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import data.LandingPageData;
import procedures2.Prodcedures;
import utils.BrowserUtils;
import utils.GlobalVariables;

public class CartPageTest extends BaseTest {
	/* WebDriver driver; */

	private Prodcedures prodcedures;
    @BeforeMethod
    public void beforeTest() {
		/*
		 * driver = BrowserUtils.getDriver(); driver.manage().window().maximize();
		 * driver.get("https://www.saucedemo.com/");
		 */
    	prodcedures = new Prodcedures(GlobalVariables.driver);
    }

    @Test
    public void goToCheckout() {
        // Initialize procedures
        prodcedures = new Prodcedures(GlobalVariables.driver);

        // Login to the landing page
//        LandingPageData landingPageData = new LandingPageData();
//        landingPageData.setUserName("standard_user");
//        landingPageData.setPassword("secret_sauce");
//        prodcedures.login(landingPageData, GlobalVariables.driver);

        // Add items to the cart
//        prodcedures.addItemsLessThanTenDollarsToCart();

        // Proceed to checkout
        prodcedures.proceedToCheckout();

        // Add assertions to verify the checkout process was initiated
    }
}