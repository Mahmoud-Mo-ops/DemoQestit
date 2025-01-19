package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
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

	@Test(dataProvider = "getLandingPageData", description = "verify log out procedures")
	public void testLoginLandingPage(LoginLandingPageData data) throws InterruptedException {
		try {
			procedures.login(data, driver);
			logOutProcedures.openHamburgerMenu()
			                .performLogOut()                
			                .assertLogout();  // This will assert the URL after logout
		} catch(AssertionError e) {
		      // Capture and attach a screenshot on failure
	       	getScreenShoot(driver, "Invalid Login Error Screenshot");
	           throw e; 
		}
		
	}


	@DataProvider
	public Object[][] getLandingPageData() throws IOException {
		// Path to the JSON file
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

		// Read the JSON file and convert it into an array of LoginLandingPageData
		LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

		// Wrap the array in an Object[][] structure
		Object[][] data = new Object[dataArray.length][1];
		for (int i = 0; i < dataArray.length; i++) {
			data[i][0] = dataArray[i];
		}
		return data;
	}

}
