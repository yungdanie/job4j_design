package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, HashSet<Path>> map = new HashMap<>();

    public void showDuplicates() {
        for (HashSet<Path> paths : map.values()) {
            if (paths.size() > 1) {
                paths.forEach(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        map.putIfAbsent(fileProperty, new HashSet<>());
        map.get(fileProperty).add(file);
        return FileVisitResult.CONTINUE;
    }
}
