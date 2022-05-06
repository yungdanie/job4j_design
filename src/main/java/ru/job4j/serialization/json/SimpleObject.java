package ru.job4j.serialization.json;

public class SimpleObject {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

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
