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
import utils.GlobalVariables;

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
		// Extract and attach JSON data (optional)
		extractData();
	}

	/* extract data from json to allure report */
	@BeforeSuite
	public void extractData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

		// Read and deserialize the JSON into an array of SubmitOrderData objects
		SubmitOrderData[] submitOrderDataArray = DataReaderUtil.getJsonDataToArray(filePath, SubmitOrderData[].class);

		// Convert the array back to a JSON string (you can customize how you want it
		// displayed)
		Gson gson = new Gson();
		String jsonContent = gson.toJson(submitOrderDataArray);

		// Attach the JSON data to the Allure report
		Allure.addAttachment("JSON Data", "application/json", jsonContent, ".json");

		// Optionally, log the content if needed
		// System.out.println("Attached JSON Data: " + jsonContent);
	}

	@BeforeTest(description = "Sets up the browser instance for each test.")
	public void initialize() throws IOException {
		// Set up WebDriver
		GlobalVariables.setDriver(BrowserUtils.getDriver());

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