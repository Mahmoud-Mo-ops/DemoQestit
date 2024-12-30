package procedures;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.ProductCataloguePage;

public class ProductCatalogueProcedures {
	private WebDriver driver;

	public ProductCatalogueProcedures(WebDriver driver) {
		this.driver = driver;
	}

	public void addItemsLessThanTenDollarsToCart() {
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
	}

	
	//error validation method
//	public void logSelectedItemName(String itemName) {
//		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
//		List<WebElement> addToCartButtons = productCataloguePage.getAddToCartButtons();
//		List<WebElement> carts = productCataloguePage.getProductPrices();
//		for (int i = 0; i < carts.size(); i++) {
//			String productName = carts.get(i).getText();
//			if (productName.contains(itemName)) {
//				//System.out.println("this buttons " + addToCartButtons.get(i));
//				System.out.println("Button " + addToCartButtons.get(i));
//				productCataloguePage.addItemToCart(addToCartButtons.get(i));
//
//			}
//			productCataloguePage.goToCart().click();
//
//		}
//
//	}
	
/*cucumber*/
	public void addProductToCart(String productName) {
	    ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
	    List<WebElement> productNames = productCataloguePage.getProductNames();
	    List<WebElement> addToCartButtons = productCataloguePage.getAddToCartButtons();
	    
	    boolean productFound = false;

	    for (int i = 0; i < productNames.size(); i++) {
	        String currentProductName = productNames.get(i).getText();
	        if (currentProductName.equalsIgnoreCase(productName)) {
	            productCataloguePage.addItemToCart(addToCartButtons.get(i));
	            productFound = true;
	            break;
	        }
	    }

	    if (!productFound) {
	        // If product is not found, throw an exception
	        throw new IllegalArgumentException("Product not available for adding to cart.");
	    }
	    
	    // Go to cart if the product was added successfully
	    productCataloguePage.goToCart().click();
	}

	
}