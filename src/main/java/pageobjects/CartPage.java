package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.GlobalVariables;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver = GlobalVariables.getDriver();

    }

    public WebElement getCheckoutButton() {
    	return driver.findElement(By.id("checkout"));
    }
    
    public WebElement getremoveButton() {
    	return driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));
    }
    
	// find element of shoppingCartTiGetNumberOfItemAddedToCart
	public WebElement shoppingCartBadgeNumberOnCartPage() {
		return driver.findElement(By.cssSelector("#shopping_cart_container > a > span"));


	}
    
}