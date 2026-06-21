package com.news.system.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static final Properties properties = new Properties();

    private ConfigLoader() {
    }

    static {

        try (InputStream is =
                     ConfigLoader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found");
            }

            properties.load(is);

            properties.put(
                    "bootstrap.servers",
                    properties.getProperty("kafka.bootstrap.servers"));

            properties.put(
                    "key.serializer",
                    properties.getProperty("key.serializer"));

            properties.put(
                    "value.serializer",
                    properties.getProperty("value.serializer"));

            properties.put(
                    "key.deserializer",
                    properties.getProperty("key.deserializer"));

            properties.put(
                    "value.deserializer",
                    properties.getProperty("value.deserializer"));

            properties.put(
                    "group.id",
                    properties.getProperty("kafka.group.id"));

            properties.put(
                    "auto.offset.reset",
                    properties.getProperty("auto.offset.reset"));

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config", e);
        }
    }

    public static long getFrequency() {
        return Long.parseLong(
                properties.getProperty("feed.frequency"));
    }

}