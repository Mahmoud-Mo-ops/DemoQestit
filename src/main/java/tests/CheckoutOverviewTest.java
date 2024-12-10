package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.CheckoutPageData;
import procedures.CheckoutOverviewProcedures;
import procedures.CheckoutPageProcedures;
import utils.BrowserUtils;

public class CheckoutOverviewTest extends BaseTest {

	//WebDriver driver;

//	public class LandingPageTest {
//
//		@BeforeMethod
//		public void setUp() {
//			// invoke edge browser
//			//driver = BrowserUtils.getDriver();
//			// need to creat file proeprt to get this url from it **
//			// driver.get(Properties.URL)
//			driver.get("https://www.saucedemo.com/");
//
//		}

		@Test
		public void gotToCheclOutOverView() {
			CheckoutOverviewProcedures.gotToCompletePage();
		}


}