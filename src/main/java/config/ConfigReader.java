package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private ConfigReader() {
        //Prevent Object creation
    }

    private static void loadProperties() {
        try (
                /* ConfigReader -> Returns value from a key
                 * ClassLoader -> Gives info about ConfigReader by  keeping it into memory
                 * getResourceAsStream -> it will pack tje project into jar so that classloader
                 * where the config properties is located as we do not give filepath */

                InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("config.properties file not found.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static long getLong(String key) {
        return Long.parseLong(get(key));
    }

    public static Boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static float getFloat(String key) {
        return Float.parseFloat(get(key));
    }
}
