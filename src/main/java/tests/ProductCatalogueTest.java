package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.ProductCatalogueProcedures;
import utils.BaseTest;
import utils.GlobalVariables;

public class ProductCatalogueTest extends BaseTest {

	ProductCatalogueProcedures addItemToCartProcedure;
	
	@BeforeMethod
	public void intialize() {
		addItemToCartProcedure= new ProductCatalogueProcedures(GlobalVariables.driver);
	}
	
	@Test(description="Verify user can successfully add items priced under ten dollars to the shopping cart.")
	public void testAddItemsLessThanTenDollarsToCart() {
		addItemToCartProcedure.addItemsLessThanTenDollarsToCart();

	}
}