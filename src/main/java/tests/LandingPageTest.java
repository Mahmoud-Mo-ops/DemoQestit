package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import data.LandingPageData;
import procedures2.Prodcedures;
import utils.BrowserUtils;
import utils.GlobalVariables;

public class LandingPageTest extends BaseTest {
	LandingPageData data = new LandingPageData();
	Prodcedures prodcedures;

	@BeforeMethod
	public void setUp() {
		//driver = BrowserUtils.getDriver();
//		driver.manage().window().maximize();
		GlobalVariables.driver.get("https://www.saucedemo.com/");
		prodcedures = new Prodcedures(GlobalVariables.driver);
	}

	@Test
	public void loginTest() {
		prodcedures.login(data, GlobalVariables.driver);
	}
}