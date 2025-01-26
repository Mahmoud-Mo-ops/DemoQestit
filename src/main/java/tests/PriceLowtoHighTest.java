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

    
    @Test(dataProvider = "getLandingPageData", description = "Tc003: Verify that the product catalog sorts items correctly when the 'Price Low to High' option is selected.")
    public void verifyPriceLowToHighSorting(LoginLandingPageData dataUsedForPriceFiltration) {    	
    	Allure.parameter("Username", dataUsedForPriceFiltration.getUserName());
    	Allure.parameter("Password",dataUsedForPriceFiltration.getPassword());
    	
        Allure.step("Open the Landing Page", () -> {
            driver.get(configReader.getUrl());
        });

        Allure.step("Login with username: " + dataUsedForPriceFiltration.getUserName()+ "and password "+ dataUsedForPriceFiltration.getPassword(), () -> {
            procedures.login(dataUsedForPriceFiltration, driver);
        });

        Allure.step("Sort Products by Price Low to High", () -> {
            productCatalogueProcedures.PriceSortingLowToHoghProcedures();
        });
        
    }
    
	@DataProvider
	public Object[] getLandingPageData() throws IOException {
	    // Path to the JSON file
	    String filePath = System.getProperty("user.dir") + "/src/main/resources/globalData.json";

	    // Read the JSON file and convert it into an array of LoginLandingPageData
	    LoginLandingPageData[] dataArray = DataReaderUtil.getJsonDataToArray(filePath, LoginLandingPageData[].class);

	 
	    return dataArray;
	}
}
