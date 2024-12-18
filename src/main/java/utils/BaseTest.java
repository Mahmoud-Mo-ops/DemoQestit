package utils;
import java.net.MalformedURLException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	@BeforeTest(description = "Sets up the browser instance and prepares the driver for the test execution.")
    public void initialize() throws MalformedURLException {
        // Initialize browser instance for the current test
        GlobalVariables.setDriver(BrowserUtils.getDriver());
    }

	@AfterTest(description = "Cleans up resources by quitting the browser instance and removing the driver after test execution.")
    public void tearDown() {
        // Quit browser after the test finishes
        if (GlobalVariables.getDriver() != null) {
            GlobalVariables.getDriver().quit();
            GlobalVariables.removeDriver();
        }
    }
}
