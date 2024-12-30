package tests;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeTest(description = "Sets up the browser instance for each test.")
    public void initialize() throws IOException {
        GlobalVariables.setDriver(BrowserUtils.getDriver());
  
    }

//    @AfterTest(description = "Cleans up resources and quits the browser after each test.")
//    public void tearDown() {
//        if (GlobalVariables.getDriver() != null) {
//            GlobalVariables.getDriver().quit();
//          
//        } 
//    }
}