package tests.cucumber;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import procedures.*;
import tests.functional.BaseTest;
import utils.GlobalVariables;

public class SubmitOrdersDefinitions extends BaseTest {
    WebDriver driver;
    ProductCatalogueProcedures addItemToCartProcedure;
    CartPageProcedures cartPageProcedures;
    CheckoutPageProcedures checkoutPageProcedures;

    // Add a constructor to initialize WebDriver and procedures
    public SubmitOrdersDefinitions() {
        this.driver = GlobalVariables.getDriver(); // Ensure WebDriver is set
        this.addItemToCartProcedure = new ProductCatalogueProcedures(driver);
        this.cartPageProcedures = new CartPageProcedures(driver);
        this.checkoutPageProcedures = new CheckoutPageProcedures(driver);
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
