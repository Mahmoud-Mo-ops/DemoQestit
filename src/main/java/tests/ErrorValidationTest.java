package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.AssertionFactory;
import utils.ConfigReader;
import utils.GlobalVariables;
import utils.Retry;

public class ErrorValidationTest extends BaseTest {
    private WebDriver driver;
    private LandingPageProcedures procedures;
    private ConfigReader configReader;
    private static final Logger logger = LogManager.getLogger(ErrorValidationTest.class);
    AssertionFactory assertionFactory;
    ProductCatalogueProcedures productCatalogueProcedures;

    @BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
    public void setUp() throws IOException {
        configReader = new ConfigReader();
        driver = GlobalVariables.getDriver();
        driver.get(configReader.getUrl());
        productCatalogueProcedures = new ProductCatalogueProcedures(driver);
        logger.info("Test environment set up successfully.");
    }

    @Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class, description = "Validates error message for invalid login attempts on the landing page.")
    @Description("This test performs an invalid login and validates the error message displayed on the landing page.")
    public void testInvalidLoginLandingPage() {
        logTestDetails(); // Attach test body to Allure

        LandingPageProcedures procedures = new LandingPageProcedures(driver);
        // Perform an invalid login and capture the error message
        procedures.LoginInvalidUserName();
        String errorMessage = procedures.getErrorMessage();

        // Log the captured error message
        logger.info("Captured error message: {}", errorMessage);

        // Initialize the AssertionFactory
        assertionFactory = new AssertionFactory(driver);

        // Define the expected error message  //Epic sadface: Password is required
        String expectedMessage = "Epic sadface:  is required";

     // Perform the assertion
        try {
            Assert.assertTrue(errorMessage.contains(expectedMessage));
            logger.info("Assertion passed: Error message matches the expected text.");
        } catch (AssertionError e) {
            // Capture and attach a screenshot on failure
        	getScreenShoot(driver, "Invalid Login Error Screenshot");
            throw e; 
        }
    }

    @Step("Logging test details for Allure.")
    private void logTestDetails() {
        Allure.addAttachment("error description", "This test validates the error message displayed during an invalid login attempt.");
    }
}
