package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import procedures.CheckoutPageProcedures;
import utils.DataReaderUtil;

public class CheckoutPageTest extends BaseTest {
    WebDriver driver;
    private CheckoutPageProcedures checkoutPageProcedures;
    final Logger logger = LogManager.getLogger(CheckoutPageTest.class);

    @BeforeMethod(description = "Sets up the test environment by initializing configurations, launching the browser, and navigating to the base URL.")
    public void setUp() throws IOException {
//        driver = GlobalVariables.driver;
        checkoutPageProcedures = new CheckoutPageProcedures(driver);
    }

    @Test(dataProvider="getCheckoutPageData" , description="Verify user can enter their shipping address (first name, last name, and postal code) and proceed to the checkout overview page")
    public void goToCheckoutOverView(CheckoutPageData data) {
        if (data == null) {
            logger.error("Received null data for checkout. Skipping test.");
            return;
        }
        logger.info("First Name: " + data.getFirstName());
        logger.debug("Last Name: " + data.getLastName());
        logger.debug("Postal Code: " + data.getPostalCode());

        checkoutPageProcedures.fillOutDataUser(data);
    }

    @DataProvider
    public CheckoutPageData[] getCheckoutPageData() throws IOException {
        // Define the file path of the JSON data
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json"; 

        // Read data from the JSON file and map it to the appropriate data type (CheckoutPageData)
        CheckoutPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, CheckoutPageData[].class);


        return dataArray; // Return the populated 2D array of checkout data
    }
}







//}