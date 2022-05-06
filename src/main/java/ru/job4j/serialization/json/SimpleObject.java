package ru.job4j.serialization.json;

public class SimpleObject {

    private String str;

    public SimpleObject() {
    }

    public SimpleObject(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleObject{");
        sb.append("str='").append(str).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
