package ru.job4j.finder;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Find {


    public static void main(String[] args) throws IOException {
        ArgsName arg = check(args);
        String d = arg.get("d");
        String n = arg.get("n");
        String t = arg.get("t");
        String o = arg.get("o");
        if (t.equals("name")) {
            fullNameSearch(d, n, o);
        } else {
            maskAndRegexSearch(d, n, t, o);
        }
    }

    private static void maskAndRegexSearch(String d, String n, String t, String o) throws IOException {
        FileMaskRegexVisitor visitor = new FileMaskRegexVisitor(n, t);
        Files.walkFileTree(Paths.get(d), visitor);
        write(o, visitor.getResult());
    }

    private static void fullNameSearch(String d, String n, String o) throws IOException {
        FileNameVisitor visitor = new FileNameVisitor(n);
        Files.walkFileTree(Paths.get(d), visitor);
        write(o, visitor.getResult());
    }

    private static void write(String o, StringBuilder result) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(o))) {
            out.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName check(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Amount of arguments is not equal to 4");
        }
        ArgsName arg = ArgsName.of(args);
        String d = arg.get("d");
        String t = arg.get("t");
        if (!Files.exists(Paths.get(d))) {
            throw new IllegalArgumentException("Start directory does not exist");
        }
        if (!"mask".equals(t) && !"name".equals(t) && !"regex".equals(t)) {
            throw new IllegalArgumentException("Search type has wrong pattern. Only \"mask\", \"name\", \"regex\" allowed");
        }
        return arg;
    }
}
