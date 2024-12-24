package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserUtils {
	private static final Logger logger = LogManager.getLogger(BrowserUtils.class);

    static Properties property = new Properties();
    public static WebDriver getDriver() throws IOException {
        WebDriver driver = null;
            // Load the config.properties file
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + "\\src\\main\\resources\\config.properties";
            FileInputStream fileStream = new FileInputStream(filePath);
            property.load(fileStream);
            
            // Get the browser and headless properties from the config file
            String browserName = property.getProperty("browser");
            String headless = property.getProperty("headless");

            // Check if the browser is set to "edge"
            if ("edge".equalsIgnoreCase(browserName)) {
                // Load the Hub URL from config
                ConfigReader configReader = new ConfigReader();
                String hubUrl = configReader.getHubURL();

                // Set the desired browser options
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");

                // Check if headless is enabled and add the headless argument if necessary
                if ("true".equalsIgnoreCase(headless)) {
                    options.addArguments("--headless");
                }

                 options.addArguments("--disable-gpu");

                // Initialize RemoteWebDriver with the Grid Hub URL and options
                driver = new RemoteWebDriver(new URL(hubUrl), options);
            } else {
                // Optionally, handle other browsers if needed
            	logger.info("The configured browser is not supported or not set to edge.");
            }

        

        return driver; // Ensure a driver is always returned
    }
    
    
}
