package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {
	
	public WebDriver driver;

	@BeforeSuite
	public void intialize() {
		GlobalVariables.driver = BrowserUtils.getDriver();
	}
	
//	
//	@AfterSuite
//	public void tearDown() {
//		GlobalVariables.driver.quit();
//	}

}
