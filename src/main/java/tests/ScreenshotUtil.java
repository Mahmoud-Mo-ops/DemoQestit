package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import io.qameta.allure.Allure;
import utils.BrowserUtils;
import utils.GlobalVariables;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ScreenshotUtil {
	
	   @BeforeTest(description = "Sets up the browser instance for each test.")
	    public void initialize() throws IOException {
	        GlobalVariables.setDriver(BrowserUtils.getDriver()); 
	    }
	   
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
