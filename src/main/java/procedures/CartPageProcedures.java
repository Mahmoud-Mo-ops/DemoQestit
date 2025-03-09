package procedures;

import org.openqa.selenium.WebDriver;

import pageobjects.CartPage;

public class CartPageProcedures  {
    private CartPage cartPage;
	private WebDriver driver;
	
    public CartPageProcedures(WebDriver driver) {
    	 cartPage=new CartPage(driver);
        this.driver =driver;
    }


    
    public void NavigateToCheckOutReview() {
    	cartPage.getCheckoutButton().click();
    }
//    
}