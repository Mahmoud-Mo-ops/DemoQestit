package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import procedures.CheckoutOverviewProcedures;
import procedures.CheckoutPageProcedures;
import utils.BrowserUtils;

public class CheckoutOverviewTest extends BaseTest {
		@Test
		public void gotToCheclOutOverView() {
			CheckoutOverviewProcedures.gotToCompletePage();
		}


}