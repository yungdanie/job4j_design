package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        FileVisitor searcher = new FileVisitor(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void check(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder does not contain two elements. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        } else if (!Files.exists(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Root folder does not exist. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        } else if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Root folder is not directory. Usage java -jar dir.jar ROOT_FOLDER FILE_EXTENSION");
        }
    }

    public static void main(String[] args) throws IOException {
        check(args);
        search(Paths.get(args[0]), x -> x.getFileName().toString().endsWith(args[1])).forEach(System.out::println);
    }

}
