package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder add = new StringBuilder();
            int read;
            int last = 1;
            while ((read = in.read()) != -1) {
                if (read != 13 && read != 10) {
                    add.append((char) read);
                    last = read;
                } else if (last % 2 == 0) {
                    stringBuilder.append(add).append(System.lineSeparator());
                    add = new StringBuilder();
                    last = 1;
                }
            }
            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
