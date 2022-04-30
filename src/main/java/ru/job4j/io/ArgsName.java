package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        check(args);
        String[] temp;
        for (String str : args) {
            temp = str.split("=", 2);
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    private boolean check(String[] args) {
        String[] temp;
        for (String str : args) {
            temp = str.split("=", 2);
            if (temp[0].isEmpty() || temp[1].isEmpty()) {
                throw new IllegalArgumentException();
            } else if (temp[0].charAt(0) != '-') {
                throw new IllegalArgumentException("Wrong argument pattern");
            }
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}