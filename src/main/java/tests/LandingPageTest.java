package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
    LandingPageProcedures procedures;
    ConfigReader configReader;

    @BeforeMethod
    public void setUp() throws IOException {
        configReader = new ConfigReader();
        driver = GlobalVariables.driver;
        driver.get(configReader.getUrl());
        procedures = new LandingPageProcedures(driver);
    }

    @Test(dataProvider = "getLandingPageData")
    public void testLoginLandingPage(LoginLandingPageData data) {
        if (data == null) {
            logger.error("Received null data for login. Skipping test.");
            return;
        }
        logger.info("UserName: " + data.getUserName());
        if (logger.isDebugEnabled()) {
            logger.debug("Password: " + data.getPassword());
        }
        procedures.login(data, driver);
    }


    @DataProvider
    public Object[][] getLandingPageData() throws IOException {
        // Define the file path of the JSON data
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json"; // Use forward slashes

        // Read data from the JSON file and map it to the appropriate data type
        LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

        // Initialize a 2D Object array to match the data provider format required by TestNG
        Object[][] data = new Object[dataArray.length][1];

        // Populate the 2D array with data for login only
        for (int i = 0; i < dataArray.length; i++) {
            // Check if the data contains username and password for login
            if (dataArray[i].getUserName() != null && dataArray[i].getPassword() != null) {
                data[i][0] = dataArray[i];  // Store valid login data
            }
        }

        return data; // Return the populated 2D array of login data
    }
}
