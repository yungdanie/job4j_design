package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] data = multiple(10);
            for (int[] array : data) {
                for (int num : array) {
                    String str = String.valueOf(num);
                    str = str + ", ";
                    out.write(str.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiple(int size) {
        int[][] data = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                data[row][cell] = (cell + 1) * (row + 1);
            }
        }
        return data;
    }
}
