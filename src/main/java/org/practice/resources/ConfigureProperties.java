package org.practice.resources;

import org.practice.enums.EnvironmentType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigureProperties {
    private static final Properties prop = new Properties();
    static {
        String env = System.getProperty("env", "QA").toUpperCase();
        EnvironmentType envType =  EnvironmentType.valueOf(env);
        String filename = envType.name().toLowerCase() + ".properties";
        String relativePath = File.separator + "src" + File.separator + "main" + File.separator + "java" +
                File.separator + "org" + File.separator + "practice" +
                File.separator + "resources" + File.separator + filename;
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + relativePath)) {
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("Properties file not found or error reading file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return System.getProperty(key) != null
                ? System.getProperty(key)
                : prop.getProperty(key);
    }

    /**
     * Gets the property value with a default fallback.
     */
    public static String getProperty(String key, String defaultValue) {
        return System.getProperty(key) != null
                ? System.getProperty(key)
                : prop.getProperty(key, defaultValue);
    }
}

