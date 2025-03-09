package tests.functional;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import data.LoginLandingPageData;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.AssertionFactory;
import utils.ConfigReader;
import utils.ExcelToJsonConverter;
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
	}

	@Test(dataProvider = "getErrorValidationData", retryAnalyzer = Retry.class)
	public void testInvalidLoginLandingPage(LoginLandingPageData loginData) {
		String username = loginData.getUserName();
		String password = loginData.getPassword();
		String testCaseId = loginData.getTestCaseId();
		String description = loginData.getDescription();

		// Set dynamic test case name
		Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testCaseId + " - " + description));

		// Attach test parameters
		Allure.parameter("Test Case ID", testCaseId);
		Allure.parameter("Description", description);
		Allure.parameter("Username", username);
		Allure.parameter("Password", password);
		logTestDetails(); // Attach test body to Allure
		LandingPageProcedures procedures = new LandingPageProcedures(driver);
		// Perform an invalid login and capture the error message
		procedures.LoginInvalidUserName(loginData.getUserName());
		String errorMessage = procedures.getErrorMessage();

		// Log the captured error message
		logger.info("Captured error message: {}", errorMessage);

		// Initialize the AssertionFactory
		assertionFactory = new AssertionFactory(driver);

		// Define the expected error message //Epic sadface: Password is required
		String expectedMessage = "Epic sadface:  is required";
		Assert.assertTrue(errorMessage.contains(expectedMessage));
	}

	@Step("Logging test details for Allure.")
	private void logTestDetails() {
		Allure.addAttachment("error description",
				"This test validates the error message displayed during an invalid login attempt.");
	}
	
	
	@DataProvider 
	public Object[] getErrorValidationData() throws IOException {
		String excelFilePath = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";
		String jsonString = ExcelToJsonConverter.convertExcelToJson(excelFilePath);
		Gson gson = new Gson();
		LoginLandingPageData[] dataArray = gson.fromJson(jsonString, LoginLandingPageData[].class);
		return Arrays.stream(dataArray).filter(data -> "ErrorValidationTest".equalsIgnoreCase(data.getTestSuite())).toArray();
	}
}
