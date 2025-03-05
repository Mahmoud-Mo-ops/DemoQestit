package tests;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import data.SubmitOrderData;
import io.qameta.allure.Allure;
import procedures.SubmitOrderPurchase;
import utils.ConfigReader;
import utils.ExcelToJsonConverter;
import utils.GlobalVariables;
import utils.Retry;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class SubmitOrderTest extends BaseTest {
    SubmitOrderPurchase submitOrderPurchase;
    ConfigReader configReader;
    WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        driver = GlobalVariables.getDriver();
        configReader = new ConfigReader();
        driver.get(configReader.getUrl());
        submitOrderPurchase = new SubmitOrderPurchase(driver);
    } 

    @Test(dataProvider = "getSubmitOrderData", retryAnalyzer = Retry.class)
    public void submitOrderTest(SubmitOrderData orderData) throws IOException {
        // Extract test data
        String username = orderData.getUsername();
        String password = orderData.getPassword();
        String firstName = orderData.getFirstName();
        String lastName = orderData.getLastName();
        String postalCode = orderData.getPostalCode();
        String testCaseId = orderData.getTestCaseId();
        String description = orderData.getDescription();
        // Attach test parameters
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", description);
        Allure.parameter("Username", username);
        Allure.parameter("Password", password);
        Allure.parameter("Postal Code", postalCode);
        // Set dynamic test case name
        Allure.getLifecycle().updateTestCase(testResult -> 
            testResult.setName(testCaseId + " - " + description));
        // Execute test steps
        submitOrderPurchase.LogIn(username, password);
        submitOrderPurchase.addItemsLessThanTenDollarsToCart();
        submitOrderPurchase.NavigateToCheckOutReview();
        submitOrderPurchase.fillOutDataUser(firstName, lastName, postalCode);
        submitOrderPurchase.gotToCompletePage(); 
        // Verify order confirmation
        String confirmationText = submitOrderPurchase.extractConfirmationText();
        Assert.assertEquals(confirmationText, "Thank you for your order!", 
            "Order confirmation message mismatch!");
    } 
    
    @DataProvider
    public Object[] getSubmitOrderData() throws IOException {
        String excelFilePath = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";
        String jsonString = ExcelToJsonConverter.convertExcelToJson(excelFilePath);
        Gson gson = new Gson(); 
        SubmitOrderData[] dataArray = gson.fromJson(jsonString, SubmitOrderData[].class);
        // Filter only test cases for SubmitOrderTest 
        return Arrays.stream(dataArray)
        	    .filter(data -> "SubmitOrderTest".equalsIgnoreCase(data.getTestSuite()))
        	    .toArray(); 
    }

}
