package procedures;

import org.openqa.selenium.WebDriver;

import pageobjects.CheckoutOverviewPage;
import utils.GlobalVariables;

public class CheckoutOverviewProcedures {
	private static WebDriver driver;

	public static void gotToCompletePage() {
		CheckoutOverviewPage checkoutOverview = new CheckoutOverviewPage(GlobalVariables.getDriver());
		checkoutOverview.getFinishButton().click();
	}

}
