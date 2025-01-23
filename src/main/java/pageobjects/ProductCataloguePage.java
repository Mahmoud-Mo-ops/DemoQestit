
package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.AbstractComponents; // Import AbstractComponents


public class ProductCataloguePage  extends AbstractComponents {
	private WebDriver driver;
	private By productCarts = By.className("inventory_item_description");
	private By productNames = By.className("inventory_item_name");
	private By productPrices = By.className("inventory_item_price");
	private By addToCartButtons = By.className("btn_inventory");
	private By goToShoppingCartButton = By.className("shopping_cart_link");
	private By arrowButtonFiltration = By.className("product_sort_container");
	private By shoppingCarBadge = By.cssSelector("#shopping_cart_container > a > span");
    private By hamburgerMenuButton = By.cssSelector("#react-burger-menu-btn");
    private By logOutButtonButton = By.cssSelector("#logout_sidebar_link");

	public ProductCataloguePage(WebDriver driver) {
        super(driver);  // Explicitly call the constructor of AbstractComponents

		this.driver = driver;
	}

	public List<WebElement> getProductCarts() {
		return driver.findElements(productCarts);
	}

	// Extract all product prices displayed on the page
	public List<WebElement> getProductPrices() {
		return driver.findElements(productPrices);
	}

	public List<WebElement> getAddToCartButtons() {
		return driver.findElements(addToCartButtons);
	}

	public List<WebElement> getProductNames() {
		return driver.findElements(productNames);
	}

	public void addItemToCart(WebElement addToCartButton) {
		addToCartButton.click();
	}

	public WebElement goToCart() {
		return driver.findElement(goToShoppingCartButton);
	}

	// Find the "Price Low to High" sorting dropdown
	public WebElement getFiltrationArrowButton() {
		return driver.findElement(arrowButtonFiltration);
	}

	// Method to select "Price Low to High" sorting option
	public void selectPriceLowToHigh() {
		WebElement filterDropdown = getFiltrationArrowButton();
		Select dropdown = new Select(filterDropdown);
		dropdown.selectByVisibleText("Price (low to high)");
	}

	// find element of shoppingCartTiGetNumberOfItemAddedToCart
	public WebElement shoppingCartBadgeNumber() {
		return driver.findElement(shoppingCarBadge);
	}
	
	// public Webelement hamburger menu
	public ProductCataloguePage ClickhamburgerMenu() {
        waitForElement(driver.findElement(hamburgerMenuButton));  // Use waitForElement for a single element
			 driver.findElement(hamburgerMenuButton).click();
			 return this ;
	}
	
	// public Welbelement LogOut Button
	public ProductCataloguePage ClickLogOutButton() {
        waitForElement(driver.findElement(logOutButtonButton));  // Use waitForElement for a single element
		 driver.findElement(logOutButtonButton).click();;
		 return this;
	}
}
