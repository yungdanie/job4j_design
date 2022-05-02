package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Path path;

    public Zip(Path path) {
        this.path = path;
    }

    public void packFiles(Path source, ZipOutputStream out, String avoid) throws IOException {
        List<Path> files = Files.list(source).filter(x -> !Files.isDirectory(x)).collect(Collectors.toList());
        List<Path> internalDir = Files.list(source).filter(Files::isDirectory).collect(Collectors.toList());
        for (Path file : files) {
            packSingleFile(file, out, avoid);
        }
        for (Path path : internalDir) {
            Path subPath = path.subpath(this.path.getNameCount(), path.getNameCount());
            out.putNextEntry(new ZipEntry(subPath + "/"));
            packFiles(path, out, avoid);
        }
    }

    public void packSingleFile(Path source, ZipOutputStream out, String avoid) {
        if (source.toString().endsWith(avoid)) {
            return;
        }
        Path subPath = source.subpath(path.getNameCount(), source.getNameCount());
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toString()))) {
            out.putNextEntry(new ZipEntry(subPath.toString()));
            out.write(in.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Path d = Paths.get(argsName.get("d"));
        String e = argsName.get("e");
        Path o = Paths.get(argsName.get("o"));
        Zip zip = new Zip(d);
        try (ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(o.toString())))) {
            if (Files.isDirectory(d)) {
                zip.packFiles(d, out, e);
            } else {
                zip.packSingleFile(d, out, e);
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
    }
}