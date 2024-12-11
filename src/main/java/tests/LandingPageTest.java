package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import data.LandingPageData;
import procedures.LandingPageProcedures;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
	LandingPageData data = new LandingPageData();
	LandingPageProcedures prodcedures;

	@BeforeMethod
	public void setUp() {
		//driver = BrowserUtils.getDriver();
//		driver.manage().window().maximize();
		GlobalVariables.driver.get("https://www.saucedemo.com/");
		prodcedures = new LandingPageProcedures(GlobalVariables.driver);
	}

	@Test
	public void loginTest() {
		prodcedures.login(data, GlobalVariables.driver);
	}
}