package tests.cucumber;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import procedures.*;
import tests.functional.BaseTest;
import utils.GlobalVariables;

public class CommonStepDefinitions extends BaseTest {
    WebDriver driver;
    LandingPageProcedures landingPageProcedures;
    ProductCatalogueProcedures addItemToCartProcedure;
    CartPageProcedures cartPageProcedures;
    CheckoutPageProcedures checkoutPageProcedures;

    @Given("I landed on Ecommerce")
    public void i_landed_on_ecommerce() throws IOException {
        initialize();
        driver = GlobalVariables.getDriver();
        driver.get("https://www.saucedemo.com/");
        landingPageProcedures = new LandingPageProcedures(driver);
        addItemToCartProcedure = new ProductCatalogueProcedures(driver);
        cartPageProcedures = new CartPageProcedures(driver);
        checkoutPageProcedures = new CheckoutPageProcedures(driver);
    }

    @Given("I am logged in with username (.+) and password (.+)$")
    public void i_am_logged_in_with_username_and_password(String username, String password) {
        landingPageProcedures.login(username, password);
    }
}
