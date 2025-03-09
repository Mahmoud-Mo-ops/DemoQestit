package tests.functional;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import procedures.ProductCatalogueProcedures;
import utils.GlobalVariables;

public class ProductCatalogueTest extends BaseTest {

	ProductCatalogueProcedures addItemToCartProcedure;
	
	@BeforeMethod(description = "Initializes the ProductCatalogueProcedures instance before each test method.")
	public void intialize() {
		addItemToCartProcedure= new ProductCatalogueProcedures(GlobalVariables.getDriver());
	}
	
	@Test(description="Verify user can successfully add items priced under ten dollars to the shopping cart.")
	public void testAddItemsLessThanTenDollarsToCart() {
		addItemToCartProcedure.addItemsLessThanTenDollarsToCart();

	}
	

}