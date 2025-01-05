package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class AllureEnvironmentFile {

    private static final String ALLURE_RESULTS_PATH = "allure-results/";

    // ConfigReader to fetch properties
    private ConfigReader configReader;

    public AllureEnvironmentFile(ConfigReader configReader) {
        this.configReader = configReader;
    }

    public void writeEnvironmentProperties() {
        Properties environmentProperties = new Properties();

        // Set properties from ConfigReader
        environmentProperties.setProperty("url", configReader.getUrl());
        environmentProperties.setProperty("HUB_URL", configReader.getHubURL());
        environmentProperties.setProperty("browser", configReader.getBrowser());
        environmentProperties.setProperty("browserVersion", configReader.getBrowserVersion());
        environmentProperties.setProperty("environment", configReader.getEnvironment());
        environmentProperties.setProperty("testPlatform", configReader.getTestPlatform());
        environmentProperties.setProperty("testExecutionDate", configReader.getTestExecutionDate());
        environmentProperties.setProperty("testFramework", configReader.getTestFramework());

        // Example: Additional environment-specific properties (optional)
//        environmentProperties.setProperty("os", System.getProperty("os.name"));
//        environmentProperties.setProperty("javaVersion", System.getProperty("java.version"));

        // Ensure the allure-results folder exists
        File allureResultsFolder = new File(ALLURE_RESULTS_PATH);
        if (!allureResultsFolder.exists()) {
            allureResultsFolder.mkdirs();
        }

        // Create the allure-environment.properties file and write to it
        File environmentFile = new File(allureResultsFolder, "allure-environment.properties");
        try (OutputStream outputStream = new FileOutputStream(environmentFile)) {
            environmentProperties.store(outputStream, "Allure Environment Properties");
            System.out.println("Environment properties written to allure-environment.properties");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
