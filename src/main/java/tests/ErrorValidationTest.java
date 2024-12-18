package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.LandingPageProcedures;
import utils.BaseTest;
import utils.ConfigReader;
import utils.GlobalVariables;

public class ErrorValidationTest extends BaseTest {
	WebDriver driver;
    LandingPageProcedures procedures;
    ConfigReader configReader;
	private static final Logger logger = LogManager.getLogger(ErrorValidationTest.class);

    
	@BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
    public void setUp() throws IOException  {
        configReader = new ConfigReader();
        driver = GlobalVariables.getDriver(); 
        driver.get(configReader.getUrl());
        procedures = new LandingPageProcedures(driver);
        }
    
    
    @Test(groups= {"ErrorValidation"}, retryAnalyzer=Retry.class, description = "Validates error message for invalid login attempts on the landing page.")
    public void testInvalidLoginLandingPage() {
    	procedures.LoginInvalidUserName();
    	//correct error message is Epic sadface: Password is required
    	procedures.validateErrorMessage("log failed");
    	procedures.getErrorMessage();
    	Assert.assertEquals(procedures.getErrorMessage(),"correct error message is Epic sadface: Password is required"); //retry done 
         
}
}
 

    
    /*
     * 
     * 
     * 
        logger.info("Testing login with invalid username: " + data.getusernameWrong());
        procedures.login(data, driver);

        // Validate the error message
      //  String expectedError = "Epic sadface: Username and password do not match any user in this service";
      //  Assert.assertTrue(procedures.validateErrorMessage(expectedError), "Error message validation failed!");
    }

    @DataProvider
    public Object[][] getLandingPageData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
        LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

        // Include both valid and invalid login credentials
        return java.util.Arrays.stream(dataArray).map(data -> new Object[] { data }).toArray(Object[][]::new);
    }

    
     * 
     * */
     