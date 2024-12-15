package tests;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import procedures.LandingPageProcedures;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LandingPageTest.class);

    WebDriver driver;
    List<LoginLandingPageData> dataList;
    LandingPageProcedures procedures;
    ConfigReader configReader;

    @BeforeMethod
    public void setUp() throws IOException {
        // Initialize config reader and procedures
        configReader = new ConfigReader();
        driver = GlobalVariables.driver; 
        driver.get(configReader.getUrl());
        procedures = new LandingPageProcedures(driver);
        //System.getProperty("user.dir") + " //src//test//java//AmazonFrameWorkDesign//data//Order.json")
        String filePath =System.getProperty("user.dir")+"\\src\\main\\resources\\globalData.json";
        dataList = DataReaderUtil.getJsonDataToList(filePath, LoginLandingPageData.class);
        // Log the data to see if it's being read correctly
        for (LoginLandingPageData data : dataList) {
            logger.info("UserNamelogger: " + data.getUserName());
            // Do not log sensitive information like password in production
            logger.info("Passwordlogger: " + data.getPassword()); // Use debug level for sensitive info if needed
        }
    }

    @Test
    public void loginTest() {
        // Perform login for each data set
        for (LoginLandingPageData data : dataList) {
            try {
                procedures.login(data, driver);
            } catch (Exception e) {
                logger.error("Error while performing login for user: " + data.getUserName(), e);
            }
        }
    }
}

