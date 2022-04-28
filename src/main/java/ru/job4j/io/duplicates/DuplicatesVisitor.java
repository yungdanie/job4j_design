package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, Path> map = new HashMap<>();

    public HashMap<FileProperty, HashSet<Path>> mapSet = new HashMap<>();

    public void showDuplicates() {
        if (mapSet != null) {
            for (HashSet<Path> paths : mapSet.values()) {
                paths.stream().iterator().forEachRemaining(System.out::println);
            }
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        Path path;
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        path = map.putIfAbsent(fileProperty, file);
        if (path != null) {
            HashSet<Path> temp = mapSet.get(fileProperty);
            if (temp == null) {
                mapSet.put(fileProperty, new HashSet<>());
                temp = mapSet.get(fileProperty);
                temp.add(file);
                temp.add(path);
            } else {
                temp.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
