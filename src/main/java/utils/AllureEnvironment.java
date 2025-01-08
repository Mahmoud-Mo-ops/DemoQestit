package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironment {
    public static void setEnvironment() {
        System.out.println("Setting up environment properties...");

        ConfigReader configReader = new ConfigReader();
        Properties properties = new Properties();
        properties.setProperty("Tester", configReader.getNameOfExcuter());
        properties.setProperty("Browser", configReader.getBrowser());
        properties.setProperty("Browser Version", configReader.getBrowserVersion());
        properties.setProperty("Operating System", System.getProperty("os.name"));
        properties.setProperty("Test Framework", configReader.getTestFramework());
        properties.setProperty("Test Machine", configReader.getTestMachine());
        properties.setProperty("Website Url", configReader.getUrl());



        File allureResultsDir = new File("target/allure-results");
        if (!allureResultsDir.exists()) {
            allureResultsDir.mkdirs();
            System.out.println("Created allure-results directory.");
        }

        try (FileWriter writer = new FileWriter("target/allure-results/environment.properties")) {
            properties.store(writer, "Allure Environment Properties");
            System.out.println("Environment properties file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
