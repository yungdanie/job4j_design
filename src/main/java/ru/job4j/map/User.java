package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }

    public User(String name, int children) {
        this.name = name;
        this.children = children;
    }
}
