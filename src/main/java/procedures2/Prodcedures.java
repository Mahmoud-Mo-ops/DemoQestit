package procedures2;

import org.openqa.selenium.WebDriver;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.LandingPage;
import pageobjects.ProductCataloguePage;
import data.LandingPageData;
import java.util.List;
import org.openqa.selenium.WebElement;

public class Prodcedures {
	private CheckoutPage checkoutPage;
	private CartPage cartPage;
	private WebDriver driver;
	private  LandingPage loginLandingPage;

	public Prodcedures(WebDriver driver) {
		this.driver = driver;
		this.cartPage = new CartPage(driver);
		loginLandingPage = new LandingPage(driver);

	}

	// Landing page
	public  void login(LandingPageData data, WebDriver driver) {
		loginLandingPage.sendKeysUserNameTextField(data.getUserName());
		loginLandingPage.sendKeysPasswordTextField(data.getPassword());
		loginLandingPage.clickLoginButton();
	}

	// Product page
	public  void addItemsLessThanTenDollarsToCart() {
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		List<WebElement> products = productCataloguePage.getProductNames();
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
	}

	// Cart page
	public void proceedToCheckout() {
		cartPage.clickOnCheckoutButton().click();
		System.out.println("clicked");
	}

	
}