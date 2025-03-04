package tests;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import data.LoginLandingPageData;
import data.SubmitOrderData;
import io.qameta.allure.Allure;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import procedures.RemoveFromCartProcedure;
import utils.ConfigReader;
import utils.DataReaderUtil;
import utils.ExcelToJsonConverter;
import utils.GlobalVariables;

public class RemoveFromCartTest extends BaseTest {
	private WebDriver driver;
	private LandingPageProcedures procedures;
	private ProductCatalogueProcedures productCatalogueProcedures;
	private ConfigReader configReader;
	private RemoveFromCartProcedure removeFromCartProcedure;

	@BeforeMethod
	public void setup() {
		configReader = new ConfigReader();
		driver = GlobalVariables.getDriver();
		// open website
		driver.get(configReader.getUrl());
		procedures = new LandingPageProcedures(driver);
		productCatalogueProcedures = new ProductCatalogueProcedures(driver);
		removeFromCartProcedure = new RemoveFromCartProcedure(driver);
	}

	@Test(dataProvider = "getLandingPageData")
	public void testRemoveFromCart(LoginLandingPageData usedDataForRemveItemTest) throws InterruptedException {
		String testCaseId = usedDataForRemveItemTest.getTestCaseId();
		String description = usedDataForRemveItemTest.getDescription();
     
		Allure.parameter("Username", usedDataForRemveItemTest.getUserName());
		Allure.parameter("Password", usedDataForRemveItemTest.getPassword());

		// Set the test case title dynamically
		Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(testCaseId + " - " + description));

		// log in
		procedures.login(usedDataForRemveItemTest, driver);

		// Step 3: Add the item to the cart
		removeFromCartProcedure.setProductName("Sauce Labs Fleece Jacket"); // Set the product name
		removeFromCartProcedure.addItemToTheCartAndVerifyTheCartCount();

		// Step 4: Click on the shopping cart to view the items
		removeFromCartProcedure.clickOnShoppingCart();

		// Step 5: Remove the item from the cart
		removeFromCartProcedure.clickOnRemoveButton();

		// Step 6: Verify the cart count updates correctly
		String updatedCartCount = removeFromCartProcedure.verifyCartCountAfterRemovingItem();
		Assert.assertEquals(updatedCartCount, "0", "The cart count is not correct after removing the item.");
	}

	@DataProvider
	public Object[] getLandingPageData() throws IOException {
		String excelFilePath = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";
		String jsonString = ExcelToJsonConverter.convertExcelToJson(excelFilePath);

		Gson gson = new Gson();
		LoginLandingPageData[] dataArray = gson.fromJson(jsonString, LoginLandingPageData[].class);

		// Filter only test cases for SubmitOrderTest
		return Arrays.stream(dataArray).filter(data -> "RemoveFromCartTest".equalsIgnoreCase(data.getTestSuite()))
				.toArray();

	}

}
