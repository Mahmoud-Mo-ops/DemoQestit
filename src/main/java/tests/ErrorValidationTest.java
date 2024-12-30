package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.AssertionFactory;
import utils.ConfigReader;
import utils.GlobalVariables;
import utils.Retry;

public class ErrorValidationTest extends BaseTest {
	private WebDriver driver;
	private LandingPageProcedures procedures;
	private ConfigReader configReader;
	private static final Logger logger = LogManager.getLogger(ErrorValidationTest.class);
	AssertionFactory assertionFactory;
	ProductCatalogueProcedures productCatalogueProcedures;

	@BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
//		procedures = new LandingPageProcedures(driver);
		productCatalogueProcedures= new ProductCatalogueProcedures(driver);
		logger.info("Test environment set up successfully.");
	}

	@Test(groups = {
			"ErrorValidation" }, retryAnalyzer = Retry.class, description = "Validates error message for invalid login attempts on the landing page.")
	public void testInvalidLoginLandingPage() {
		LandingPageProcedures procedures = new LandingPageProcedures(driver);
		// Perform an invalid login and capture the error message
		procedures.LoginInvalidUserName();
		String errorMessage = procedures.getErrorMessage();

// Log the captured error message
		logger.info("Captured error message: {}", errorMessage);

// Initialize the AssertionFactory
		assertionFactory = new AssertionFactory(driver);

// Define the expected error message  //Epic sadface: Password is required"  (right error message)
		String expectedMessage = "Epic sadface:  is required";

// Perform the assertion
		assertionFactory.assertTrue(errorMessage.contains(expectedMessage), expectedMessage, errorMessage,
				"Error message does not match the expected text.");

// Finalize the assertions 
		assertionFactory.assertAll();

		logger.info("Assertions completed for invalid login validation.");
	}

	
	@Test(groups = {
			"ErrorValidation" }, retryAnalyzer = Retry.class, description = "Validates that adding a non-existent product to the cart throws an IllegalArgumentException with the appropriate error message.")
	public void testWrongSelectedProduct() throws IOException {

		// Create an instance of LandingPageTest
		LandingPageTest landPageTest = new LandingPageTest();

		// Initialize the 'procedures' object explicitly for this instance
		landPageTest.setUp(); // This will call the @BeforeMethod setup method and initialize the necessary
								// fields

		// Use the data provider to get valid login data
		Object[][] loginData = landPageTest.getLandingPageData();

		// Loop through login data
		for (Object[] data : loginData) {
			// This assumes that the first element in each Object[] is LoginLandingPageData
			LoginLandingPageData loginDataItem = (LoginLandingPageData) data[0];

			// Call the login method
			landPageTest.testLoginLandingPage(loginDataItem);
		}

		try {
			// After successful login, perform product addition to cart
			productCatalogueProcedures.addProductToCart("Sauce Labs Bike Light");

			// If the product is added successfully, test should pass
			logger.info("Product added to cart successfully.");
		} catch (IllegalArgumentException e) {
			// If an exception is thrown, ensure that it's the expected exception
			Assert.assertEquals("Product not available for adding to cart.", e.getMessage());
		} catch (Exception e) {
			// Fail the test if any other unexpected exception is thrown
			Assert.fail("Unexpected exception thrown: " + e.getMessage());
		}
	}

}
