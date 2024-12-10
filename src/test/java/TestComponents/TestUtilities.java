package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import qestit.pageObjects.LandingPage;

public class TestUtilities {
	/* create global object to be acceses for all classes */
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties property = new Properties();
		// Get the current working directory
		String currentDir = System.getProperty("user.dir");
		// Construct the file path relative to the current working directory
		String filePath = currentDir + "\\src\\main\\java\\qestit\\resources\\GolbalData.properties";

		FileInputStream fileStream = new FileInputStream(filePath);
		property.load(fileStream);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: property.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions option = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new EdgeDriver(option);
			// to avoid any flaky failure (not visible)
			 //driver.manage().window().setSize(new Dimension(1800, 1000));
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chrome.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	//screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
	}

	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToWebsite();
		return landingPage;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
		// String to HashMap (From Jackson Datbind)
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
}

//	 @AfterMethod
//	public void teardown() {
//	
//		if (driver != null) {
//			driver.quit(); 
//		}
//	 }
//}