package ru.job4j.finder;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileNameVisitor extends SimpleFileVisitor<Path> {

    private final String fullName;

    private final StringBuilder result = new StringBuilder();

    public FileNameVisitor(String searchName) {
        this.fullName = searchName;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getFileName().toString().equals(fullName)) {
            result.append(file).append(System.lineSeparator());
        }
        return super.visitFile(file, attrs);
    }

    public StringBuilder getResult() {
        return result;
    }
}
