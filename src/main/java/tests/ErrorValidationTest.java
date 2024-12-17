//package tests;
//
//import java.io.IOException;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import procedures.LandingPageProcedures;
//import utils.BaseTest;
//import utils.ConfigReader;
//import utils.GlobalVariables;
//
//public class ErrorValidationTest extends BaseTest {
//	WebDriver driver;
//    LandingPageProcedures procedures;
//    ConfigReader configReader;
//
//    
//
//    @BeforeMethod
//    public void setUp() throws IOException  {
//        configReader = new ConfigReader();
//        driver = GlobalVariables.driver; 
//        driver.get(configReader.getUrl());
//        procedures = new LandingPageProcedures(driver);
//        
//        }
//    
//
//    
//    @Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class)
//    public void loginErrorValidation() throws IOException, InterruptedException {
//        // Initialize LandingPageProcedures with the global driver
//        LandingPageProcedures landingPageProcedures = new LandingPageProcedures(driver);
//
//        // Perform login with invalid credentials
//        landingPageProcedures.Errorlogin("eid");
//
////        // Capture the error message
////        String errorMail = landingPageProcedures.catchErrorMessage();
////
////        // Assert that the error message is as expected
////        Assert.assertEquals(errorMail, "Epic sadface: Password is required");
//    }
//}
