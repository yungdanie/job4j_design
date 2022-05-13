package ru.job4j.finder;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMaskRegexVisitor extends SimpleFileVisitor<Path> {

    private PathMatcher pathMatcher;

    private final StringBuilder result = new StringBuilder();

    public FileMaskRegexVisitor(String pattern, String t) {
        if (t.equals("mask")) {
            this.pathMatcher = FileSystems.getDefault().getPathMatcher(String.format("%s:%s", "glob", pattern));
        } else {
            this.pathMatcher = FileSystems.getDefault().getPathMatcher(String.format("%s:%s", "regex", pattern));
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pathMatcher.matches(file.getFileName())) {
            result.append(file).append(System.lineSeparator());
        }
        return super.visitFile(file, attrs);
    }

    public StringBuilder getResult() {
        return result;
    }
}