package ru.job4j.serialization.json;

import java.io.*;
import java.nio.file.Path;
import java.util.Formatter;
import java.util.Scanner;

public class SimpleObject implements Serializable {

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


    public static void main(String[] args) {
        SimpleObject object = new SimpleObject();
        try (ObjectOutputStream abc = new ObjectOutputStream(new FileOutputStream("class.txt"))) {
            abc.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
