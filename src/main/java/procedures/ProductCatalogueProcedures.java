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
	        //productNames
	        List<WebElement> productNames = productCataloguePage.getProductNames();
	        //Buttons
	        List<WebElement> addToCartButtons = productCataloguePage.getAddToCartButtons();
	        //carts
	        List<WebElement> ListOfProductCarts=productCataloguePage.getProductCarts();

	        for (int i = 0; i < productNames.size(); i++) {
	            String currentProductName = productNames.get(i).getText();
	            if (currentProductName.equalsIgnoreCase("Sauce Labs Bolt T-Shirt")) {
	                productCataloguePage.addItemToCart(addToCartButtons.get(i));
		            System.out.println("this is product of cucumnber " +currentProductName);
	                break;
	            }
	        }
	        productCataloguePage.goToCart().click();
	    }

	
}