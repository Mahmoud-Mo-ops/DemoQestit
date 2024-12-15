package procedures;

import org.openqa.selenium.WebDriver;

import pageobjects.CartPage;
import utils.GlobalVariables;

public class CartPageProcedures  {
    private CartPage cartPage;
	private WebDriver driver;
    public CartPageProcedures(WebDriver driver) {
        this.driver =GlobalVariables.driver;
        cartPage=new CartPage(driver);
    }


    
    public void NavigateToCheckOutReview() {
    	cartPage.getCheckoutButton().click();
    }
//    
}