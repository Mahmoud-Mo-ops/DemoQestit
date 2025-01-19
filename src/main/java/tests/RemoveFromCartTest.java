package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.LoginLandingPageData;
import io.qameta.allure.Allure;
import procedures.LandingPageProcedures;
import procedures.ProductCatalogueProcedures;
import procedures.RemoveFromCartProcedure;
import utils.ConfigReader;
import utils.DataReaderUtil;
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
		procedures = new LandingPageProcedures(driver);
		productCatalogueProcedures = new ProductCatalogueProcedures(driver);
		removeFromCartProcedure= new RemoveFromCartProcedure(driver);
	}

	@Test(dataProvider = "getLandingPageData")
	public void testRemoveFromCart(LoginLandingPageData data) {
		Allure.parameter("Username", data.getUserName());
		Allure.parameter("Password", data.getPassword());
		// log in
		Allure.step("Open the Landing Page", () -> {
			driver.get(configReader.getUrl());
		});

		Allure.step("Login with username: " + data.getUserName() + "and password " + data.getPassword(), () -> {
			procedures.login(data, driver);
		});

		// Step 3: Add the item to the cart
		Allure.step("Add Sauce Labs Fleece Jacket to the cart", () -> {
			removeFromCartProcedure.setProductName("Sauce Labs Fleece Jacket"); // Set the product name
			removeFromCartProcedure.addItemToTheCartAndVerifyTheCartCount(); // Add item to cart and verify the cart
																				// count
		});

		// Step 4: Click on the shopping cart to view the items
		Allure.step("Click on the shopping cart", () -> {
			removeFromCartProcedure.clickOnShoppingCart();
		});

		// Step 5: Remove the item from the cart
		Allure.step("Remove the Sauce Labs Fleece Jacket from the cart", () -> {
			removeFromCartProcedure.clickOnRemoveButton();
		});
		// Step 6: Verify the cart count updates correctly
		Allure.step("Verify the cart count updates correctly after removing the item", () -> {
	        String updatedCartCount = removeFromCartProcedure.verifyCartCountAfterRemovingItem();

	        Assert.assertEquals(updatedCartCount, "0", "The cart count is not correct after removing the item.");
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
