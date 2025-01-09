package tests;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import io.qameta.allure.Allure;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.GlobalVariables;

public class PriceLowtoHighTest extends BaseTest {
    private WebDriver driver;
    private LandingPageProcedures procedures;
    private ProductCatalogueProcedures productCatalogueProcedures;
    private ConfigReader configReader;

    // Before each test method, perform login
    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();
        driver = GlobalVariables.getDriver();
        procedures = new LandingPageProcedures(driver);
        productCatalogueProcedures = new ProductCatalogueProcedures(driver);
    }

    // Test method for Price Low to High Sorting
    @Test(dataProvider = "getLandingPageData")
    public void verifyPriceLowToHighSorting(LoginLandingPageData data) {
        Allure.step("Open the Landing Page", () -> {
            driver.get(configReader.getUrl());
        });

        Allure.step("Login with username: " + data.getUserName(), () -> {
            procedures.login(data, driver);
        });

        Allure.step("Sort Products by Price Low to High", () -> {
            productCatalogueProcedures.PriceSortingLowToHoghProcedures();
        });
    }

    // Data provider for login credentials
    @DataProvider
    public Object[][] getLandingPageData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";
        // Deserialize the JSON data into an array of LoginLandingPageData objects
        LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);
        // Filter only valid login credentials
        return filterValidLoginData(dataArray);
    }

    // Helper method to filter valid login credentials
    private Object[][] filterValidLoginData(LoginLandingPageData[] dataArray) {
        return Arrays.stream(dataArray)
                .filter(data -> data.getUserName() != null && data.getPassword() != null)
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }
}
