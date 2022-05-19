package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private String path;

    private final Properties prop = new Properties();

    private Config(String properties) {
        this.path = properties;
    }

    private void load(InputStream io) {
        try {
            this.prop.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return prop.getProperty(key);
    }

    public static Config getConfig(String properties) {
        Config config = new Config(properties);
        ClassLoader classLoader = config.getClass().getClassLoader();
        config.load(classLoader.getResourceAsStream(properties));
        return config;
    }
}
