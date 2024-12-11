package procedures;

import org.openqa.selenium.WebDriver;

import data.LandingPageData;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.LandingPage;

public class LandingPageProcedures {
//	private CheckoutPage checkoutPage;
//	private CartPage cartPage;
	private WebDriver driver;
	private  LandingPage loginLandingPage;

	public LandingPageProcedures(WebDriver driver) {
		this.driver = driver;
		loginLandingPage = new LandingPage(driver);

	}

	// Landing page
	public  void login(LandingPageData data, WebDriver driver) {
		loginLandingPage.sendKeysUserNameTextField(data.getUserName());
		loginLandingPage.sendKeysPasswordTextField(data.getPassword());
		loginLandingPage.clickLoginButton();
	}
}