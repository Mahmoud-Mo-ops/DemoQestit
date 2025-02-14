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
    
    
    
    @Test(dataProvider = "getLandingPageData" )
    public void verifyPriceLowToHighSorting(LoginLandingPageData dataUsedForPriceFiltration) throws InterruptedException {    	
    	
    	 // Extract test data
        String username = dataUsedForPriceFiltration.getUserName();
        String password = dataUsedForPriceFiltration.getPassword();
        String testCaseId = dataUsedForPriceFiltration.getTestCaseId();
        String description = dataUsedForPriceFiltration.getDescription();

        // Set dynamic test case name
        Allure.getLifecycle().updateTestCase(testResult -> 
            testResult.setName(testCaseId + " - " + description));

        // Attach test parameters
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", description);
        Allure.parameter("Username", username);
        Allure.parameter("Password", password);

    	Allure.parameter("Username", dataUsedForPriceFiltration.getUserName());
    	Allure.parameter("Password",dataUsedForPriceFiltration.getPassword());
    	
    	    driver.get(configReader.getUrl());
    	    procedures.login(dataUsedForPriceFiltration, driver);
    	    productCatalogueProcedures.PriceSortingLowToHoghProcedures();
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
