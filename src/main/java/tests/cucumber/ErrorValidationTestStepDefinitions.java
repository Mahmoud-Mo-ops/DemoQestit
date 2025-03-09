package tests.cucumber;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import procedures.CartPageProcedures;
import procedures.CheckoutPageProcedures;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import tests.functional.BaseTest;
import utils.GlobalVariables;

public class ErrorValidationTestStepDefinitions extends BaseTest {
    WebDriver driver;
    LandingPageProcedures landingPageProcedures;
    ProductCatalogueProcedures addItemToCartProcedure;
    CartPageProcedures cartPageProcedures;
    CheckoutPageProcedures checkoutPageProcedures;

    public ErrorValidationTestStepDefinitions() {
        this.driver = GlobalVariables.getDriver(); // Ensure WebDriver is set
        this.landingPageProcedures = new LandingPageProcedures(driver);
        this.addItemToCartProcedure = new ProductCatalogueProcedures(driver);
        this.cartPageProcedures = new CartPageProcedures(driver);
        this.checkoutPageProcedures = new CheckoutPageProcedures(driver);
    }

    @Given("logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
        landingPageProcedures.login(username, password);
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = landingPageProcedures.getErrorMessage();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), 
            "Expected error message: " + expectedErrorMessage + " but got: " + actualErrorMessage);
    }
}
