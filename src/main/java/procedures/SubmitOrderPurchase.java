package procedures;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import pageobjects.CartPage;
import pageobjects.CheckoutOverviewPage;
import pageobjects.CheckoutPage;
import pageobjects.CompletePage;
import pageobjects.LandingPage;
import pageobjects.ProductCataloguePage;
import tests.functional.*;
import utils.GlobalVariables;

public class SubmitOrderPurchase extends BaseTest {
    WebDriver driver;

    public SubmitOrderPurchase(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Logging in on the landing page with username: {0} and password: {1}")
    public SubmitOrderPurchase LogIn(String username, String password) {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.sendKeysUserNameTextField(username);
        landingPage.sendKeysPasswordTextField(password);
        landingPage.clickLoginButton();
        return this;
    }

    @Step("Adding products priced under $10 to the cart")
    public SubmitOrderPurchase addItemsLessThanTenDollarsToCart() {
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        List<WebElement> carts = productCataloguePage.getProductCarts();
        List<WebElement> prices = productCataloguePage.getProductPrices();
        List<WebElement> addToCartButtons = productCataloguePage.getAddToCartButtons();

        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            if (price < 10.0) {
                productCataloguePage.addItemToCart(addToCartButtons.get(i));
            }
        }
        productCataloguePage.goToCart().click();
        return this;
    }

    @Step("Navigating to the checkout review page by clicking the checkout button")
    public SubmitOrderPurchase NavigateToCheckOutReview() {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();
        return this;
    }

    @Step("Filling out the shipping information with first name: {0}, last name: {1}, postal code: {2}")
    public SubmitOrderPurchase fillOutDataUser(String firstName, String lastName, String postalCode) {
        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.sendFirstNameTextField(firstName)
                    .sendLastNameTextField(lastName)
                    .sendPostalCodeTextField(postalCode)
                    .clickOnContinueButton();
        return this;
    }

    @Step("Clicking the 'Finish' button in the checkout overview to complete the order")
    public SubmitOrderPurchase gotToCompletePage() {
        CheckoutOverviewPage checkoutOverview = new CheckoutOverviewPage(GlobalVariables.getDriver());
        checkoutOverview.getFinishButton().click();
        return this;
    }

    @Step("Verifying the order confirmation message to ensure the order was successfully Placed")
    public String extractConfirmationText() {
        CompletePage completePage = new CompletePage(driver);
        return completePage.findConfirmationText().getText();
    }
}