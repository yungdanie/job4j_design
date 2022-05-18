package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final Properties prop = new Properties();

    private Config() {
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

    public static Config getConfig() {
        Config config = new Config();
        ClassLoader classLoader = config.getClass().getClassLoader();
        config.load(classLoader.getResourceAsStream("app.properties"));
        return config;
    }
}
