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
import utils.BaseTest;
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
        configReader = new ConfigReader();
        driver = GlobalVariables.driver; 
        driver.get(configReader.getUrl());
        procedures = new LandingPageProcedures(driver);
        String filePath =System.getProperty("user.dir")+"\\src\\main\\resources\\globalData.json";
        dataList = DataReaderUtil.getJsonDataToList(filePath, LoginLandingPageData.class);
        for (LoginLandingPageData data : dataList) {
            logger.info("UserNamelogger: " + data.getUserName());
            logger.debug("Passwordlogger: " + data.getPassword()); // Use debug level for sensitive info if needed
        }
    }

    @Test
    public void loginTest() {
        for (LoginLandingPageData data : dataList) {
            try {
                procedures.login(data, driver);
            } catch (Exception e) {
                logger.error("Error while performing login for user: " + data.getUserName(), e);
            }
        }
    }
}

