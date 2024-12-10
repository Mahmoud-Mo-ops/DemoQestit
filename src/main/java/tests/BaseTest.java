package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import procedures2.Prodcedures;
import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {
	
	public WebDriver driver;

	@BeforeSuite
	public void intialize() {
		GlobalVariables.driver = BrowserUtils.getDriver();
		System.out.println(driver);
	}
	
	
	@AfterSuite
	public void tearDown() {
		GlobalVariables.driver.quit();
	}

}
