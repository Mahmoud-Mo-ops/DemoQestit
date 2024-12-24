
package tests;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {

    @BeforeTest(description = "Sets up the browser instance for each test.")
    public void initialize() throws IOException {
        // Initialize the WebDriver for each test method, so each test runs in isolation
        GlobalVariables.setDriver(BrowserUtils.getDriver());
    }

    @AfterTest(description = "Cleans up resources and quits the browser after each test.")
    public void tearDown() {
        // Quit the WebDriver after each test to ensure no WebDriver sharing
        if (GlobalVariables.getDriver() != null) {
            GlobalVariables.getDriver().quit();
            GlobalVariables.removeDriver();
        }
    }
}
