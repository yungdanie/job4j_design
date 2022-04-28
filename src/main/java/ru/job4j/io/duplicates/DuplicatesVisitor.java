package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, Path> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path path;
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        path = map.putIfAbsent(fileProperty, file);
        if (path != null) {
            Files.delete(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
