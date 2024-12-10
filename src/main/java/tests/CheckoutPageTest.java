package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import procedures.CheckoutPageProcedures;
import utils.GlobalVariables;

public class CheckoutPageTest extends BaseTest {
	private CheckoutPageProcedures checkoutPageProcedures;
	private CheckoutPageData data;

	@BeforeMethod
	public void setUp() {
		// Call the setup method from the parent class to initialize the driver and
		// login
		//super.setUp();
		data = new CheckoutPageData();
		// Initialize CheckoutPageProcedures with the driver
		checkoutPageProcedures = new CheckoutPageProcedures();
	}

	@Test()
	public void goToCheckoutOverview() {
		checkoutPageProcedures.fillOutDataUser(data);
		// Add assertions to verify the checkout process was initiated
	}
}