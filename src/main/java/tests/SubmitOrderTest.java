package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import procedures.SubmitOrderPurchase;
import utils.ConfigReader;
import utils.GlobalVariables;

public class SubmitOrderTest extends BaseTest {
	SubmitOrderPurchase submitOrderPurchase;
	ConfigReader configReader;

	@Test
	public void submitOrderTest() throws IOException {
		WebDriver driver = GlobalVariables.getDriver();
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
		submitOrderPurchase = new SubmitOrderPurchase(driver);
		// submitOrderPurchase.initialize();
		submitOrderPurchase.LogIn();
		submitOrderPurchase.addItemsLessThanTenDollarsToCart();
		submitOrderPurchase.NavigateToCheckOutReview();
		submitOrderPurchase.fillOutDataUser("firstName", "lastName", "postalcode");
		SubmitOrderPurchase.gotToCompletePage();
		String confirmationText = submitOrderPurchase.extractConfirmationText();
       Assert.assertEquals(confirmationText, "Thank you for your order!");

	}
}
