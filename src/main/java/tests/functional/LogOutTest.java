package tests.functional;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import data.LoginLandingPageData;
import data.SubmitOrderData;
import io.qameta.allure.Allure;
import pageobjects.ProductCataloguePage;
import procedures.LandingPageProcedures;
import procedures.LogOutProcedures;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.ExcelToJsonConverter;
import utils.GlobalVariables;

public class LogOutTest extends BaseTest {
	WebDriver driver;
	LandingPageProcedures procedures;
	LogOutProcedures logOutProcedures;
	ProductCataloguePage productCataloguePage;
	ConfigReader configReader;

	private static final Logger logger = LogManager.getLogger(LogOutTest.class);

	@BeforeMethod
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
		logOutProcedures = new LogOutProcedures(driver);
	}

	@Test(dataProvider = "getLandingPageData")
	public void testLogOut(LoginLandingPageData dataLogin) throws IOException {

		String username = dataLogin.getUserName();
		String password = dataLogin.getPassword();
		String testCaseId = dataLogin.getTestCaseId();
		String description = dataLogin.getDescription();

		// Set dynamic test case name
		Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testCaseId + " - " + description));

		// Attach test parameters
		Allure.parameter("Test Case ID", testCaseId);
		Allure.parameter("Description", description);
		Allure.parameter("Username", username);
		Allure.parameter("Password", password);
	

		procedures.login(dataLogin, driver);
		LogOutProcedures.assertLogout();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/"),
				"URL does not contain expected URL after logout. Actual URL: " + currentUrl);

	}

	@DataProvider
	public Object[] getLandingPageData() throws IOException {
		String excelFilePath = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";
		String jsonString = ExcelToJsonConverter.convertExcelToJson(excelFilePath);
		Gson gson = new Gson();
		LoginLandingPageData[] dataArray = gson.fromJson(jsonString, LoginLandingPageData[].class);
		return Arrays.stream(dataArray).filter(data -> "LogOutTest".equalsIgnoreCase(data.getTestSuite())).toArray();
	}

}
