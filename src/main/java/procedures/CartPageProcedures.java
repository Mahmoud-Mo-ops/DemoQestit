package procedures;

import org.openqa.selenium.WebDriver;
import pageobjects.CartPage;

public class CartPageProcedures {
    private CartPage cartPage;
	private WebDriver driver;

    public CartPageProcedures(WebDriver driver) {
        this.driver = driver;
    }

    public void proceedToCheckout() {
        cartPage.clickOnCheckoutButton().click();
        System.out.println("clicked");
    }
}