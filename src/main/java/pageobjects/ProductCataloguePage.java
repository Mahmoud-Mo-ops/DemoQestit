
package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductCataloguePage {
	private By productCarts = By.className("inventory_item_description");
	private By productNames=By.className("inventory_item_name");
	private By productPrices = By.className("inventory_item_price");
	private By addToCartButtons = By.className("btn_inventory");
	private By goToShoppingCartButton = By.className("shopping_cart_link");
	private By arrowButtonFiltration = By.className("product_sort_container");

	private WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
            this.driver = driver;
	}

	public List<WebElement> getProductCarts() {
		return driver.findElements(productCarts);
	}

	//Extract all product prices displayed on the page
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
}
