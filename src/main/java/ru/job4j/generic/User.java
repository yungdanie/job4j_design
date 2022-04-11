package ru.job4j.generic;

public class User extends Base {

    private String id;
    private String name;

    public User(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getUsername() {
        return name;
    }
}
