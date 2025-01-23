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
import pageobjects.ProductCataloguePage;
import procedures.LandingPageProcedures;
import procedures.LogOutProcedures;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;

public class LogOutTest extends BaseTest {
	WebDriver driver;
	LandingPageProcedures procedures;
	LogOutProcedures logOutProcedures;
	ProductCataloguePage productCataloguePage;
	ConfigReader configReader;
 
	private static final Logger logger = LogManager.getLogger(LogOutTest.class);
	@BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
		logOutProcedures = new LogOutProcedures(driver);
	}
	
	@Test(dataProvider = "getLandingPageData", description = "TC005: Verify the logout functionality to ensure the user is successfully logged out and redirected to the login page.")
	public void testLoginLandingPage(LoginLandingPageData dataLogin) throws InterruptedException {
		
			procedures.login(dataLogin, driver);
			LogOutProcedures.assertLogout();
			String currentUrl = driver.getCurrentUrl();
			Assert.assertTrue(currentUrl.contains("https://www.saucedemo.com/"),
					"URL does not contain expected URL after logout. Actual URL: " + currentUrl);
		
		
	}

  
	@DataProvider
	public LoginLandingPageData[] getLandingPageData() throws IOException {
		// Path to the JSON file
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

		// Read the JSON file and convert it into an array of LoginLandingPageData
		LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

		// Wrap the array in an Object[][] structure
//		Object[][] data = new Object[dataArray.length][1];
//		for (int i = 0; i < dataArray.length; i++) {
//			data[i][0] = dataArray[i];
//		}
		return dataArray;
	}

}
