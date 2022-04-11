package ru.job4j.generic;

public class Role extends Base {
    private String role;
    private String id;

    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
