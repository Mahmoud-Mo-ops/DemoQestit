package tests;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.qameta.allure.Attachment;
import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {

	@BeforeTest(description = "Sets up the browser instance for each test.")
	public void initialize() throws IOException {
		GlobalVariables.setDriver(BrowserUtils.getDriver());
	}

	@AfterTest(description = "Cleans up resources and quits the browser after each test.")
	public void tearDown() {
		if (GlobalVariables.getDriver() != null) {
			GlobalVariables.getDriver().quit();
		}
	}

	// Capture screenshot and attach to Allure report
	@Attachment(value = "Screenshot on Failure", type = "image/png")
	public byte[] captureScreenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) GlobalVariables.getDriver(); // Use GlobalVariables.getDriver() here
		File source = ts.getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(source); // Convert file to byte array
		return fileContent; // Return byte array to be attached to Allure
	}
}
