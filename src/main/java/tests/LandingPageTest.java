package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import procedures.LandingPageProcedures;
import utils.BaseTest;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
	private static final Logger logger = LogManager.getLogger(LandingPageTest.class);

	WebDriver driver;
	LandingPageProcedures procedures;
	ConfigReader configReader;

	@BeforeMethod
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.driver;
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
	}

	@Test(dataProvider = "getLandingPageData")
	public void testLoginLandingPage(LoginLandingPageData data) {
		logger.info("Testing login with valid username: " + data.getUserName());
		procedures.login(data, driver);

		// Add validation for successful login
	//	Assert.assertTrue(procedures.isLoginSuccessful(), "Login was not successful!");
	}

	@DataProvider
	public Object[][] getLandingPageData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
		LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

		// Filter only valid login credentials
		return filterValidLoginData(dataArray);
	}

	private Object[][] filterValidLoginData(LoginLandingPageData[] dataArray) {
		return java.util.Arrays.stream(dataArray)
				.filter(data -> data.getUserName() != null && data.getPassword() != null)
				.map(data -> new Object[] { data }).toArray(Object[][]::new);
	}
}
