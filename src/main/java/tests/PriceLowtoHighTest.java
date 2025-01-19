package tests;

import java.io.IOException;

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

    
    @Test(dataProvider = "getLandingPageData")
    public void verifyPriceLowToHighSorting(LoginLandingPageData data) {    	
    	Allure.parameter("Username", data.getUserName());
    	Allure.parameter("Password",data.getPassword());
    	
        Allure.step("Open the Landing Page", () -> {
            driver.get(configReader.getUrl());
        });

        Allure.step("Login with username: " + data.getUserName()+ "and password "+ data.getPassword(), () -> {
            procedures.login(data, driver);
        });

        Allure.step("Sort Products by Price Low to High", () -> {
            productCatalogueProcedures.PriceSortingLowToHoghProcedures();
        });
        
    }
    
	@DataProvider
	public Object[][] getLandingPageData() throws IOException {
	    // Path to the JSON file
	    String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

	    // Read the JSON file and convert it into an array of LoginLandingPageData
	    LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

	    // Wrap the array in an Object[][] structure
	    Object[][] data = new Object[dataArray.length][1];
	    for (int i = 0; i < dataArray.length; i++) {
	        data[i][0] = dataArray[i];
	    }
	    return data;
	}
}
