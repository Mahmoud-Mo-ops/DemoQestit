package tests;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.LandingPageProcedures;
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

	@BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
		logger.info("Test environment set up successfully.");
	}

	@Test(groups = {
			"ErrorValidation" }, retryAnalyzer = Retry.class, description = "Validates error message for invalid login attempts on the landing page.")
	public void testInvalidLoginLandingPage() {

// Perform an invalid login and capture the error message
		procedures.LoginInvalidUserName();
		String errorMessage = procedures.getErrorMessage();

// Log the captured error message
		logger.info("Captured error message: {}", errorMessage);

// Initialize the AssertionFactory
		assertionFactory = new AssertionFactory(driver);

// Define the expected error message
		String expectedMessage = "Epic sadface: Password is required";

// Perform the assertion
		assertionFactory.assertTrue(errorMessage.contains(expectedMessage), expectedMessage, errorMessage,
				"Error message does not match the expected text.");

// Finalize the assertions
		assertionFactory.assertAll();

		logger.info("Assertions completed for invalid login validation.");
	}
	
	/*
	//error validation method
	@Test(dataProvider = "getLandingPageData",description="verify the selcted prodcut")
	public void testSelectedProduct(LoginLandingPageData data) {
		procedures.login(data, driver);
		ProductCatalogueProcedures productCatalogueProcedures= new ProductCatalogueProcedures(driver);
		productCatalogueProcedures.logSelectedItemName("Sauce Labs Bolt T-Shirt");		
	}
	
	
	@DataProvider
	public Object[][] getLandingPageData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
		LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

		// Filter only valid login credentials
		return filterValidLoginData(dataArray);
	}

	private Object[][] filterValidLoginData(LoginLandingPageData[] dataArray) {
		return Arrays.stream(dataArray)
				.filter(data -> data.getUserName() != null && data.getPassword() != null)
				.map(data -> new Object[] { data }).toArray(Object[][]::new);
	}
*/
}


