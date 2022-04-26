package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] data = multiple(10);
            for (int[] array : data) {
                out.write(Arrays.toString(array).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
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
