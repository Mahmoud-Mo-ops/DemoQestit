package tests.functional;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utils.AllureEnvironment;
import utils.BrowserUtils;
import utils.ConfigReader;
import utils.ExtractJsonData;
import utils.GlobalVariables;
import utils.ScreenshotUtil;

public class BaseTest {

    private ConfigReader configReader;

    @BeforeSuite(description = "Sets up the environment for Allure reporting.")
    public void setupEnvironment() throws IOException {
        // Initialize ConfigReader
        configReader = new ConfigReader();
        // Environment section
        AllureEnvironment.setEnvironment();
    }

    @BeforeTest(description = "Sets up the browser instance for each test.")
    public void initialize() throws IOException {
        // Set up WebDriver
        GlobalVariables.setDriver(BrowserUtils.getDriver());
        // Attach JSON data for each test case (added here to ensure it appears in the report)
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
        ExtractJsonData.extractData(filePath);
    }

    // Capture screenshot after each test method
    @AfterMethod(description = "Takes a screenshot after each test method.")
    public void captureScreenshotAfterTest(ITestResult result) {
        WebDriver driver = GlobalVariables.getDriver();
        if (driver != null) {
            String testName = result.getName(); // Fetches the test case name
            ScreenshotUtil.captureScreenshot(driver, testName + "_Screenshot");
        }
    }

    // Quit browser after all tests in the suite are finished
    @AfterTest(description = "Cleans up resources and quits the browser after each test.")
    public void tearDown() {
        // Clean up the driver and quit the browser
        if (GlobalVariables.getDriver() != null) {
            GlobalVariables.getDriver().quit();
        }
    }
}
