package procedures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.CartPage;
import utils.GlobalVariables;

public class CartPageProcedures  {
    private CartPage cartPage;
	private WebDriver driver;
    private By checkoutButton = By.id("checkout");

    public CartPageProcedures(WebDriver driver) {
        this.driver =GlobalVariables.driver;
        cartPage=new CartPage(driver);
    }


    
    public void NavigateToCheckOutReview() {
    	cartPage.getCheckoutButton().click();
        // driver.findElement(checkoutButton).click();
    }
//    
}