package com.news.system.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

    private ConfigLoader() {
    }

    static {
        try (InputStream is =
                     ConfigLoader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config", e);
        }
    }

    public static String getHost() {
        return properties.getProperty("server.host");
    }

    public static int getPort() {
        return Integer.parseInt(
                properties.getProperty("server.port"));
    }

    public static long getFrequency() {
        return Long.parseLong(
                properties.getProperty("feed.frequency"));
    }

}