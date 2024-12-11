package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("utils/config.properties");
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("config.properties file not found in the classpath");
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }
}