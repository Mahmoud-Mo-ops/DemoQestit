package utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	@BeforeTest
	public void intialize() throws MalformedURLException, URISyntaxException {

      	GlobalVariables.driver = BrowserUtils.getDriver();
	}

	@AfterTest
	public void tearDown() {
		GlobalVariables.driver.quit();
	}

}
