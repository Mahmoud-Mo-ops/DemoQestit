package utils;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserUtils {
	public static WebDriver getDriver() {
		WebDriver driver;
		try {
			// Load the Hub URL from config
			ConfigReader configReader = new ConfigReader();
			String hubUrl = configReader.getHubURL();

			// Set the desired browser options
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--start-maximized");

			// Initialize RemoteWebDriver with the Grid Hub URL and options
			driver = new RemoteWebDriver(new URL(hubUrl), options);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid Selenium Grid Hub URL");
		}
		return driver;
	}
}
