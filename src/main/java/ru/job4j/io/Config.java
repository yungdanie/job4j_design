package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String temp;
       try (BufferedReader in = new BufferedReader(new FileReader(path))) {
           while (in.ready()) {
               temp = in.readLine();
               if (!temp.startsWith("#") && !temp.isEmpty()) {
                   String[] array = temp.split("=", 2);
                   if (array.length < 2) {
                       throw new IllegalArgumentException();
                   }
                   values.put(array[0], array[1]);
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public String value(String key) {
        return values.get(key);
    }

    public static void main(String[] args) {
        Config config = new Config("./app.properties");
        config.load();
    }
}