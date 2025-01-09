package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.SubmitOrderData;
import io.qameta.allure.Allure;
import procedures.SubmitOrderPurchase;
import utils.AllureEnvironment;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.ExtractJsonData;
import utils.GlobalVariables;

public class SubmitOrderTest extends BaseTest {
	SubmitOrderPurchase submitOrderPurchase;
	ConfigReader configReader;
	WebDriver driver;


	@BeforeMethod
	public void setup() throws IOException {
		driver = GlobalVariables.getDriver();
		configReader = new ConfigReader();
		driver.get(configReader.getUrl());
		submitOrderPurchase = new SubmitOrderPurchase(driver);

	}

	@Test(dataProvider = "getSubmitOrderData", description = "End To End Testing, adding products to the cart, completing the checkout process, filling shipping information, and verifying order confirmation.")
	public void submitOrderTest(String username, String password, String firstName, String lastName, String postalCode)
			throws IOException {
		// Log the login step
		Allure.step("Logging in on the landing page with username: " + username + " and password: " + password, () -> {
			submitOrderPurchase.LogIn(username, password);
		});

		// Add items to the cart
		Allure.step("Adding items to the cart", () -> {
			submitOrderPurchase.addItemsLessThanTenDollarsToCart();
		});

		// Navigate to checkout review page
		Allure.step("Navigating to checkout review", () -> {
			submitOrderPurchase.NavigateToCheckOutReview();
		});

		// Fill out user data for checkout
		Allure.step("Filling out shipping adresse using first Name : " + firstName + "and last Name " + lastName
				+ " and  postal code: " + postalCode, () -> {
					submitOrderPurchase.fillOutDataUser(firstName, lastName, postalCode);
				});

		// Go to the completion page
		Allure.step("Navigating to the complete page", () -> {
			submitOrderPurchase.gotToCompletePage();
		});
		// Verifying the order confirmation message
		Allure.step("Verifying the order confirmation message to ensure the order was successfully placed", () -> {
			String confirmationText = submitOrderPurchase.extractConfirmationText();
			Assert.assertEquals(confirmationText, "Thank you for your order!");
		});
	}

	@DataProvider()
	public Object[][] getSubmitOrderData() throws IOException {
		// Path to the JSON file
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

		// Read the JSON file and convert it into an array of SubmitOrderData
		SubmitOrderData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, SubmitOrderData[].class);

		// Convert array to Object[][] for DataProvider
		Object[][] data = new Object[dataArray.length][5];
		for (int i = 0; i < dataArray.length; i++) {
			data[i][0] = dataArray[i].getUsername();
			data[i][1] = dataArray[i].getPassword();
			data[i][2] = dataArray[i].getFirstName();
			data[i][3] = dataArray[i].getLastName();
			data[i][4] = dataArray[i].getPostalCode();
		}

		return data;
	}

}
