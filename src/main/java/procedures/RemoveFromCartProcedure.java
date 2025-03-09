package procedures;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import pageobjects.CartPage;
import pageobjects.ProductCataloguePage;

public class RemoveFromCartProcedure {
	String productName;
	ProductCataloguePage productCataloguePage;
	CartPage cartPage;
	WebDriver driver ;

	// Constructor to initialize ProductCataloguePage
	public RemoveFromCartProcedure(WebDriver driver) {
		 this.driver = driver;
         productCataloguePage = new ProductCataloguePage(driver);
         cartPage = new CartPage(driver);
	}

	
	// Set the product name for adding to cart
	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Find "Add to Cart" button for a specific product
	public WebElement findAddToCartButtonForProduct(String productName) {
		List<WebElement> productContainers = productCataloguePage.getProductCarts();

		for (WebElement product : productContainers) {
			if (product.getText().contains(productName)) {
				return product.findElement(By.className("btn_inventory"));
			}
		}
		return null;
	}

	// Add an item to the cart and verify the cart count
	@Step("addItemToTheCartAndVerifyTheCartCount")
	public void addItemToTheCartAndVerifyTheCartCount() throws InterruptedException {
		WebElement addToCartButton = findAddToCartButtonForProduct(productName);
		if (addToCartButton != null) {
			addToCartButton.click();
			System.out.println(productName + " added to the cart.");
			// Verify the cart count
		String cartCountAfterAddingItems = productCataloguePage.shoppingCartBadgeNumber().getText();
		System.out.println("number of cart after adding  cartCountAfterAddingItems " + cartCountAfterAddingItems);

			if (cartCountAfterAddingItems.equals("1")) {
				System.out.println("Cart count is correct: " + cartCountAfterAddingItems);
			} else {
				System.out.println("Cart count is incorrect. Expected: 1, Found: " + cartCountAfterAddingItems);
			}
		}
	}

	// Click on the shopping cart
	@Step("Click on the shopping cart")
	public void clickOnShoppingCart() {
		productCataloguePage.goToCart().click();
		System.out.println("click done sucessfully");
	}

	// Click on the remove button
	@Step("Remove the Sauce Labs Fleece Jacket from the cart")
	public void clickOnRemoveButton() {
		
		cartPage.getRemoveButton().click();
		System.out.println("click remove done sucessfully");

	}

	// Ensure the cart count updates correctly after removing an item
	@Step("Verify the cart count updates correctly after removing the item")
	public String verifyCartCountAfterRemovingItem() {
	    // Try to find the cart count element after removal
		WebElement cartCountAfterRemoveItem = null;
		try {
		    cartCountAfterRemoveItem = productCataloguePage.shoppingCartBadgeNumber();
		} catch (Exception e) {
		    // If the element is not found, it means it has been removed
		    System.out.println("Cart count span has been successfully deleted after removal.");
		    return "0";  // Return "0" if cart is empty
		}

		// Return the cart count after removal
		return cartCountAfterRemoveItem.getText();
	}

}
