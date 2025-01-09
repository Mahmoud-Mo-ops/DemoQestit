package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;

import data.SubmitOrderData;
import io.qameta.allure.Allure;
import utils.AllureEnvironment;
import utils.BrowserUtils;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.ExtractJsonData;
import utils.GlobalVariables;
import utils.ScreenshotUtil;

public class BaseTest {

	private ConfigReader configReader;
	// private AllureEnvironmentFile allureEnvironmentFile;
	private static final String JSON_FILE_PATH = "path/to/submit_order_data.json"; // Path to your JSON file
	private SubmitOrderData[] jsonData;

	@BeforeSuite(description = "Sets up the environment for Allure reporting.")
	public void setupEnvironment() throws IOException {
		// Initialize ConfigReader
	   configReader = new ConfigReader();
		//environment section
		AllureEnvironment.setEnvironment();
		//  Attach JSON data to Allure at the suite level
//	    String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
//		ExtractJsonData.extractData(filePath);
	}

	@BeforeTest(description = "Sets up the browser instance for each test.")
	public void initialize() throws IOException {
		// Set up WebDriver
		GlobalVariables.setDriver(BrowserUtils.getDriver());
		// Attach JSON data for each test case (added here to ensure it appears in the report)
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
        ExtractJsonData.extractData(filePath);
	}

	/* screen shoot method */
	public void getScreenShoot(WebDriver driver, String screenshotName) {
		ScreenshotUtil.captureScreenshot(driver, "Invalid Login Error Screenshot");
	}

	/* close window */
	@AfterTest(description = "Cleans up resources and quits the browser after each test.")
	public void tearDown() {
		if (GlobalVariables.getDriver() != null) {
			GlobalVariables.getDriver().quit();
		}
	}

}