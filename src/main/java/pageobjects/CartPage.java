package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GlobalVariables;

public class CartPage {
	private WebDriver driver;
	private By checkOutButton = By.id("checkout");
	private By removeButton = By.id("remove-sauce-labs-fleece-jacket");
	private By shoppingCartBadgeNumber = By.cssSelector("#shopping_cart_container > a > span");

	public CartPage(WebDriver driver) {
		this.driver = GlobalVariables.getDriver();
	}

	public WebElement getCheckoutButton() {
		return driver.findElement(checkOutButton);
	}

	public WebElement getRemoveButton() {
		return driver.findElement(removeButton);
	}

	// Get the number of items added to the cart
	public WebElement getShoppingCartItemCount() {
		return driver.findElement(shoppingCartBadgeNumber);

	}

}