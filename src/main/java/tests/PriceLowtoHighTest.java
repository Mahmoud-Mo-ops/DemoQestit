package tests;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import data.LoginLandingPageData;
import io.qameta.allure.Allure;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import utils.ConfigReader;
import utils.ExcelToJsonConverter;
import utils.GlobalVariables;

public class PriceLowtoHighTest extends BaseTest {
    private WebDriver driver;
    private LandingPageProcedures procedures;
    private ProductCatalogueProcedures productCatalogueProcedures;
    private ConfigReader configReader;

    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();
        driver = GlobalVariables.getDriver();
       driver.get(configReader.getUrl()); // 🔥 Ensure URL is opened

        procedures = new LandingPageProcedures(driver);
        productCatalogueProcedures = new ProductCatalogueProcedures(driver);
    }
 
    @Test(dataProvider = "getLandingPageData")
    public void verifyPriceLowToHighSorting(LoginLandingPageData dataUsedForPriceFiltration) throws InterruptedException {
        // Extract test data
        String username = dataUsedForPriceFiltration.getUserName();
        String password = dataUsedForPriceFiltration.getPassword();
        String testCaseId = dataUsedForPriceFiltration.getTestCaseId();
        String description = dataUsedForPriceFiltration.getDescription();

        // Set dynamic test case name in Allure
        Allure.getLifecycle().updateTestCase(testResult -> 
            testResult.setName(testCaseId + " - " + description));

        // Attach test parameters in Allure
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", description);
        Allure.parameter("Username", username);
        Allure.parameter("Password", password);

        // Perform test steps
        procedures.login(dataUsedForPriceFiltration, driver);
        productCatalogueProcedures.PriceSortingLowToHighProcedures();
    }

    @DataProvider
    public Object[] getLandingPageData() throws IOException {
        String excelFilePath = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";
        String jsonString = ExcelToJsonConverter.convertExcelToJson(excelFilePath);
        
        Gson gson = new Gson();
        LoginLandingPageData[] dataArray = gson.fromJson(jsonString, LoginLandingPageData[].class); // ✅ Corrected Data Type
        return Arrays.stream(dataArray)
                .filter(data ->"PriceLowtoHighTest".equalsIgnoreCase(data.getTestSuite())) 
                .toArray();  
    }
}
