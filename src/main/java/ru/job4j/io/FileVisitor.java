package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;

public class FileVisitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> condition;
    private ArrayList<Path> list = new ArrayList<>();

    public FileVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    public ArrayList<Path> getPaths() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
