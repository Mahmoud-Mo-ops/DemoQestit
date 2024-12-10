package Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.TestUtilities;
import qestit.pageObjects.CartPage;
import qestit.pageObjects.CheckOutPage;
import qestit.pageObjects.CheckoutOverview;
import qestit.pageObjects.CompletePage;
import qestit.pageObjects.ProductCatalogPage;

public class SubmitOrderTest extends TestUtilities {
	private String selectedProduct; // Define the product name you want to verify

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void submitOrderTest(HashMap<String, String> input) {
		ProductCatalogPage productCatalogPage = landingPage.login(input.get("userName"), input.get("password"));
		Optional<String> productName = productCatalogPage.findAndAddProductsByPrice(10);
		if (productName.isPresent()) {
			selectedProduct = productName.get();
			System.out.println(selectedProduct); //get the product name 
			CartPage cartPage = productCatalogPage.goToCartPage();
			Boolean match = cartPage.verifyProduct(selectedProduct);
			System.out.println(match);//console TRUE 
			Assert.assertTrue(match, "The selected product was not found in the cart.");
			CheckOutPage checkOutPage = cartPage.makeCheckOut();
			CheckoutOverview checkoutOverview = checkOutPage.fillCheckoutDetails(input.get("firstName"),
					input.get("lastName"), input.get("postalCode"));
			CompletePage completePage = checkoutOverview.confirm();
			String confirmationText = completePage.verifcationText();
			Assert.assertEquals(confirmationText, "Thank you for your order!");

		} else {
			Assert.fail("No products found under the specified price.");
		}
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//QestitProject//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }};
	}

}
