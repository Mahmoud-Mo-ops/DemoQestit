package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import data.JsonDataReader;
import procedures.CheckoutPageProcedures;
import utils.GlobalVariables;

public class CheckoutPageTest extends BaseTest {
	private CheckoutPageProcedures checkoutPageProcedures;
	private CheckoutPageData data;

	@BeforeMethod
	public void setUp() {
		data = new CheckoutPageData();
		checkoutPageProcedures = new CheckoutPageProcedures();
	}

	@Test()
	public void goToCheckoutOverview() throws IOException {
		data=JsonDataReader.getCutomerAdressInformation();
		checkoutPageProcedures.fillOutDataUser(data);
	}
}