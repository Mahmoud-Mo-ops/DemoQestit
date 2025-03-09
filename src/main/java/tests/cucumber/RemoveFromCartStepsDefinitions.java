package tests.cucumber;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import procedures.RemoveFromCartProcedure;
import tests.functional.BaseTest;
import utils.GlobalVariables;
import org.testng.Assert;

public class RemoveFromCartStepsDefinitions extends BaseTest {
    WebDriver driver;
    RemoveFromCartProcedure removeFromCartProcedure;

    // Constructor to initialize WebDriver and procedures
    public RemoveFromCartStepsDefinitions() {
        this.driver = GlobalVariables.getDriver();
        this.removeFromCartProcedure = new RemoveFromCartProcedure(driver);
    }

    @When("I add the product (.+) to the cart$")
    public void i_add_the_product_to_cart(String productName) throws InterruptedException {
        removeFromCartProcedure.setProductName(productName);
        removeFromCartProcedure.addItemToTheCartAndVerifyTheCartCount();
    }

    @When("I navigate to the shopping cart")
    public void i_navigate_to_shopping_cart() {
        removeFromCartProcedure.clickOnShoppingCart();
    }

    @When("I remove the product (.+) from the cart$")
    public void i_remove_product_from_cart(String productName) {
        removeFromCartProcedure.clickOnRemoveButton();
    }

    @Then("the cart count should be (.+)$")
    public void the_cart_count_should_be(String expectedCount) {
        String updatedCartCount = removeFromCartProcedure.verifyCartCountAfterRemovingItem();
        Assert.assertEquals(updatedCartCount, expectedCount, "The cart count is incorrect after removing the item.");
    }
}
