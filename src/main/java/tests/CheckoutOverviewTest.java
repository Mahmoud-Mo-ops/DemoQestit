package tests;

import org.testng.annotations.Test;

import procedures.CheckoutOverviewProcedures;

public class CheckoutOverviewTest extends BaseTest {
		@Test(description="Verify user can successfully navigate to the completion page after entering all necessary information during checkout and confirm the selected product is correctly displayed.")
		public void gotToCompletePage() {
			CheckoutOverviewProcedures.gotToCompletePage();
		}


}