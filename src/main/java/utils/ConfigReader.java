package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import freemarker.log.Logger;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;
public class ConfigReader {
    private static final Logger logger = Logger.getLogger(ConfigReader.class.getName());
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("config.properties file loaded successfully.");
            } else {
                logger.error("config.properties file not found in the classpath.");
                throw new RuntimeException("config.properties file not found in the classpath");
            }
        } catch (IOException e) {
            logger.error("Error while loading config.properties file: " + e.getMessage(), e);
            throw new RuntimeException("Error while reading config.properties", e);
        }
    }

    public String getUrl() {
        String url = properties.getProperty("url", "http://default-url.com");
        logger.debug("Fetched URL from config: " + url);
        return url;
    }

    public String getHubURL() {
        String hubURL = properties.getProperty("HUB_URL", "http://172.26.240.1:4444");
        logger.debug("Fetched HUB_URL from config: " + hubURL);
        return hubURL;
    }

    // Environment-related properties
    public String getEnvironment() {
        String environment = properties.getProperty("environment", "Unknown");
        logger.debug("Fetched environment: " + environment);
        return environment;
    }

    public String getTestPlatform() {
        return properties.getProperty("testPlatform", "Unknown");
    } 

    public String getBrowser() {
        return properties.getProperty("browser", "Unknown");
    }

    public String getBrowserVersion() {
        return properties.getProperty("browserVersion", "Unknown");
    }

    public String getTestExecutionDate() {
        return properties.getProperty("testExecutionDate", "Unknown");
    }

    public String getTestFramework() {
        return properties.getProperty("testFramework", "Unknown");
    }
    
  

    public void addEnvironmentVariablesToAllure() {
        // Fetch environment details from config
        String environment = getEnvironment();
        String url = getUrl();
        String hubUrl = getHubURL();
        String browser = getBrowser();
        String browserVersion = getBrowserVersion();
        String testPlatform = getTestPlatform();
        String testExecutionDate = getTestExecutionDate();
        String testFramework = getTestFramework();

        // Add these details to Allure environment file
        Allure.getLifecycle().updateTestCase(testCase -> {
            testCase.getLabels().add(new Label().setName("environment").setValue(environment));
            testCase.getLabels().add(new Label().setName("url").setValue(url));
            testCase.getLabels().add(new Label().setName("hubUrl").setValue(hubUrl));
            testCase.getLabels().add(new Label().setName("browser").setValue(browser));
            testCase.getLabels().add(new Label().setName("browserVersion").setValue(browserVersion));
            testCase.getLabels().add(new Label().setName("testPlatform").setValue(testPlatform));
            testCase.getLabels().add(new Label().setName("testExecutionDate").setValue(testExecutionDate));
            testCase.getLabels().add(new Label().setName("testFramework").setValue(testFramework));
        });
    }

    
    
    
}
