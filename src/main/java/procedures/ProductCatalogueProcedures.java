package procedures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.ProductCataloguePage;

public class ProductCatalogueProcedures {
	private WebDriver driver;
	ProductCataloguePage productCataloguePage;

	public ProductCatalogueProcedures(WebDriver driver) {
		this.driver = driver;
	 this.productCataloguePage = new ProductCataloguePage(driver); 
	}

	/* find all products under 10$ and add them to cart */
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

	/* cucumber */
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

	
	/*method for Price Low to High */
	public void PriceSortingLowToHoghProcedures() {
		//get pirce elements*
		List<WebElement> priceElements = productCataloguePage.getProductPrices(); 
		//initialze an empty array
		List<Double> extractedPrices = new ArrayList<>();
       
		for (WebElement element : priceElements) {
			String priceText = element.getText().replace("$", "").trim();
			extractedPrices.add(Double.parseDouble(priceText));
		}
		// Sort the extracted prices in ascending order
		List<Double> sortedPrices = new ArrayList<>(extractedPrices);
		System.out.println("this is prices before sorting " + sortedPrices);
		Collections.sort(sortedPrices);

        // Apply "Price Low to High" filter
   
		//get on flitration Button
		WebElement filterButton = productCataloguePage.getFiltrationArrowButton();
		//click on the Button 
		filterButton.click();
		   try {
		        Thread.sleep(2000); // Replace with WebDriverWait for better practice
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		
		//get hight to low option 
		 productCataloguePage.selectPriceLowToHigh();
		//click on the high to low price option 
		
		// Wait for the sorting to take effect (use explicit waits)
	    try {
	        Thread.sleep(2000); // Replace with WebDriverWait for better practice
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
		
		  // Re-fetch the price elements after sorting
		//get pirce elements*
				List<WebElement> sortedPriceElements  = productCataloguePage.getProductPrices(); 
				//initialze an empty array
				List<Double> displayedPrices  = new ArrayList<>();
				
				  for (WebElement element : sortedPriceElements) {
				        String priceText = element.getText().replace("$", "").trim();
				        displayedPrices.add(Double.parseDouble(priceText)); // Corrected list
				    }
				
				 // Compare the two lists
		        if (displayedPrices.equals(sortedPrices)) {
		            System.out.println("Sorting by 'Price Low to High' is working correctly.");
		        } else {
		            System.out.println("Sorting by 'Price Low to High' failed.");
		            System.out.println("Expected: " + sortedPrices);
		            System.out.println("Actual: " + displayedPrices);
		        }

	}
	
        

}