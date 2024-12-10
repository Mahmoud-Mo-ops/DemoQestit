package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserUtils {

	public static WebDriver getDriver() {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		return driver;
	}

}