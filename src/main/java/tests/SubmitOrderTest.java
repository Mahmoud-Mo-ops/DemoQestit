package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.SubmitOrderData;
import io.qameta.allure.Allure;
import procedures.SubmitOrderPurchase;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;
import utils.Retry;

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

	@Test(dataProvider = "getSubmitOrderData",retryAnalyzer = Retry.class)
	public void submitOrderTest(SubmitOrderData orderData)
			throws IOException {
		 // Access data directly using orderData object
	    String username = orderData.getUsername();
	    String password = orderData.getPassword();
	    String firstName = orderData.getFirstName();
	    String lastName = orderData.getLastName();
	    String postalCode = orderData.getPostalCode();
	    String testCaseId = orderData.getTestCaseId();
	    String description = orderData.getDescription();
	    
	    // Set the test case title dynamically
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testCaseId + " - " + description));
	    
	   //add paramters
	    Allure.parameter("Test Case ID", testCaseId);
	    Allure.parameter("Description", description);
	    Allure.parameter("Username", username);
	    Allure.parameter("Password", password);
	    Allure.parameter("Postal Code", postalCode);
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

	@DataProvider
	public Object[] getSubmitOrderData() throws IOException {
	    // Path to the JSON file
	    String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

	    // Read the JSON file and convert it into an array of SubmitOrderData
	    SubmitOrderData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, SubmitOrderData[].class);
	    // Return the array of SubmitOrderData directly, TestNG will inject it into the test
	    return dataArray;
	    //dataArray is an object from the array SumbitorderData
	}


}
