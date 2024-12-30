package procedures;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.CartPage;
import pageobjects.CheckoutOverviewPage;
import pageobjects.CheckoutPage;
import pageobjects.CompletePage;
import pageobjects.LandingPage;
import pageobjects.ProductCataloguePage;
import tests.BaseTest;
import utils.GlobalVariables;

public class SubmitOrderPurchase extends BaseTest {
	WebDriver driver;

	public SubmitOrderPurchase(WebDriver driver) {
		this.driver = GlobalVariables.getDriver();
	}

	// intialize Driver and open browser
	public void openBrowser() throws IOException {
		initialize();
	}

    public void performCompleteOrderProcess(String firstName, String lastName, String postalCode) throws IOException {
        LogIn();
        addItemsLessThanTenDollarsToCart();
        NavigateToCheckOutReview();
        fillOutDataUser(firstName, lastName, postalCode);
        gotToCompletePage();
    }

	// log in (landing page)
	public SubmitOrderPurchase LogIn() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.sendKeysUserNameTextField("standard_user");
		landingPage.sendKeysPasswordTextField("secret_sauce");
		landingPage.clickLoginButton();
		return this;
	}

	// find all products with price less than 10 dollar
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

	// navigate to checkout overview page //cart Page
	public SubmitOrderPurchase NavigateToCheckOutReview() {
		CartPage cartPage = new CartPage(driver);
		cartPage.getCheckoutButton().click();
		return this;
	}

	// fill out data in the checkout page

	public SubmitOrderPurchase fillOutDataUser(String firstName, String lastName, String postalCode) {
		CheckoutPage checkoutPage = new CheckoutPage();
		checkoutPage.sendFirstNameTextField(firstName).sendLastNameTextField(lastName)
				.sendPostalCodeTextField(postalCode).clickOnContinueButton();
		return this;
	}

	// check out overview

	public  SubmitOrderPurchase gotToCompletePage() {
		CheckoutOverviewPage checkoutOverview = new CheckoutOverviewPage(GlobalVariables.getDriver());
		checkoutOverview.getFinishButton().click();
		return this;
	}

	// complete page
	public String extractConfirmationText() {
		CompletePage completePage = new CompletePage(driver);
		return completePage.findConfirmationText().getText();
	}
}