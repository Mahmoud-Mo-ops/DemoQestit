package tests;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import procedures.LandingPageProcedures;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LandingPageTest.class);

	WebDriver driver;
	LandingPageProcedures procedures;
	ConfigReader configReader;

	@BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
	}

	@Test(dataProvider = "getLandingPageData", description = "Verify user can log in to the landing page using a valid username and password, and successfully submit the login form.")
	public void testLoginLandingPage(LoginLandingPageData data) {
		logger.info("Testing login with valid username: " + data.getUserName());
		procedures.login(data, driver); 
	}

	@DataProvider
	public Object[][] getLandingPageData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
		// deserialize the JSON data into an array of LoginLandingPageData objects.
		LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);
		// Filter only valid login credentials
		return filterValidLoginData(dataArray);
	}

	private Object[][] filterValidLoginData(LoginLandingPageData[] dataArray) {
		return Arrays.stream(dataArray).filter(data -> data.getUserName() != null && data.getPassword() != null)
				.map(data -> new Object[] { data }).toArray(Object[][]::new); 
	}
	
	
}