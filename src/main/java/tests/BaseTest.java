package tests;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.BrowserUtils;
import utils.GlobalVariables;

public class BaseTest {
	


	@BeforeSuite
	public void intialize() throws MalformedURLException, URISyntaxException {
		GlobalVariables.driver = BrowserUtils.getDriver();
	}
	
      	@AfterSuite
     	public void tearDown() {
		GlobalVariables.driver.quit();
        }

}

