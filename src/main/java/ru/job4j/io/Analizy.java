package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            String temp;
            while (in.ready()) {
                temp = in.readLine();
                if (temp.startsWith("400") || temp.startsWith("500")) {
                    out.write(temp.split(" ")[1] + ";");
                    while (in.ready()) {
                        temp = in.readLine();
                        if (temp.startsWith("200") || temp.startsWith("300")) {
                            out.write(temp.split(" ")[1]  + ";" + System.lineSeparator());
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.csv");
    }
}