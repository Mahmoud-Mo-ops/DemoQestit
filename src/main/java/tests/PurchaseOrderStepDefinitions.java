package tests;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import procedures.*;
import utils.GlobalVariables;

public class PurchaseOrderStepDefinitions extends BaseTest {
    WebDriver driver;
    LandingPageProcedures procedures;
    ProductCatalogueProcedures addItemToCartProcedure;
    CartPageProcedures cartPageProcedures;
    CheckoutPageProcedures checkoutPageProcedures;

    @Given("I landed on Ecommerce")
    public void i_landed_on_ecommerce() throws IOException {
        initialize();
        driver = GlobalVariables.getDriver();
        driver.get("https://www.saucedemo.com/");

        // Initialize procedure objects
        procedures = new LandingPageProcedures(driver);
        addItemToCartProcedure = new ProductCatalogueProcedures(driver);
        cartPageProcedures = new CartPageProcedures(driver);
        checkoutPageProcedures = new CheckoutPageProcedures(driver);
    }

    @Given("I am logged in with username (.+) and password (.+)$")
    public void i_am_logged_in_with_username_and_password(String username, String password) {
        procedures.login(username, password);
    }

    @When("I add a product (.+) to the cart$")
    public void i_add_a_product_to_the_cart(String productName) {
        addItemToCartProcedure.addProductToCart(productName);
    }

    @When("I proceed to the cart page, proceed with checkout, and click on the checkout button")
    public void i_proceed_to_the_cart_page_proceed_with_checkout_and_click_on_the_checkout_button() {
        cartPageProcedures.NavigateToCheckOutReview();
    }

    @When("I fill the billing information on the checkout page with First Name (.+), Last Name (.+), and Postal Code (.+)$")
    public void i_fill_the_billing_information_on_the_checkout_page_with_first_name_last_name_and_postal_code(
    	    String firstName, String lastName, int postalCode) {
    	    checkoutPageProcedures.fillOutDataUser(firstName, lastName, String.valueOf(postalCode));
    	}


    @When("I complete the order on the checkout overview page by clicking on the finish button")
    public void i_complete_the_order_on_the_checkout_overview_page_by_clicking_on_the_finish_button() {
        CheckoutOverviewProcedures.gotToCompletePage();
    }

    @Then("{string} message is displayed on the confirmation page")
    public void message_is_displayed_on_the_confirmation_page(String expectedMessage) {
        String confirmationText = CompletePageProcedures.extractConfirmationText(GlobalVariables.getDriver());
        Assert.assertEquals(confirmationText, expectedMessage);
    }
}
