package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        HashMap<String, Integer> set = new HashMap<>();
        ArrayList<String> listOfStr = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        try (Scanner in = new Scanner(new FileReader(argsName.get("path")))) {
            int index = 0;
            listOfStr.add(in.nextLine());
            for (String elem : listOfStr.get(0).split(";")) {
                set.put(elem, index++);
            }
            while (in.hasNext()) {
                listOfStr.add(in.nextLine());
            }
        }
        HashSet<String> filters = checkFilter(set, argsName.get("filter").split(","));
        for (String elements : listOfStr) {
            String[] separElements = elements.split(";");
            Iterator<String> iterator = filters.iterator();
            while (iterator.hasNext()) {
                result.add(separElements[set.get(iterator.next())]);
                if (iterator.hasNext()) {
                    result.add(";");
                }
            }

            result.add(System.lineSeparator());
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(argsName.get("out")))) {
            for (String s : result) {
                out.write(s);
            }
        }
    }

    private static HashSet<String> checkFilter(HashMap<String, Integer> set, String[] filter) {
        HashSet<String> filters = new HashSet<>();
        for (String str : filter) {
            if (set.get(str) == null) {
                throw new IllegalArgumentException("CSV file does not contain one or more fields");
            }
            filters.add(str);
        }
        return filters;
    }

    private static void checkArguments(ArgsName args) {
        Path csvFile = Paths.get(args.get("path"));
        if (!Files.exists(csvFile)) {
            throw new IllegalArgumentException("Source file does not exist");
        } else if (!csvFile.toString().endsWith(".csv")) {
            throw new IllegalArgumentException("Source name does not end with \".csv\"");
        }
        if (args.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Delimiter is not specified");
        }
        if (!args.get("out").equals("stdout") && !Files.exists(Paths.get(args.get("out")).getParent())) {
            throw new IllegalArgumentException("Illegal result file");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        checkArguments(argsName);
        handle(argsName);
    }

}
