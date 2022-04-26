package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            String string = "";
            StringBuilder add = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                string = string + (char) read;
            }
            for (String num : string.split("\r\n")) {
                if (Integer.parseInt(num) % 2 == 0) {
                    add.append(num).append("\r\n");
                }
            }
            System.out.println(add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
