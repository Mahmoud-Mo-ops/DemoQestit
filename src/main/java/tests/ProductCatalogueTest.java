package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import procedures2.Prodcedures;
import utils.GlobalVariables;

public class ProductCatalogueTest extends BaseTest {

	Prodcedures prodcedures;
	
	@BeforeMethod
	public void intialize() {
		prodcedures= new Prodcedures(GlobalVariables.driver);
	}
	
	@Test
	public void testAddItemsLessThanTenDollarsToCart() {
		prodcedures.addItemsLessThanTenDollarsToCart();

	}
}