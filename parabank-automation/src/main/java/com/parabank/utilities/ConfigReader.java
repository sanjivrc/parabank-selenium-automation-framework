package com.parabank.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads key/value pairs from config.properties so values like browser
 * and baseUrl are never hardcoded inside test or page classes.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Could not load config.properties from " + CONFIG_PATH, e);
            }
        }
    }

    public static String get(String key) {
        loadProperties();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicitWait"));
    }
}
