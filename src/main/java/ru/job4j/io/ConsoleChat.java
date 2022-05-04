package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Random random = new Random();
        List<String> phrases = readPhrases();
        List<String> logs = new ArrayList<>();
        String current;
        String answer = "Dialog start";
        Scanner scanner = new Scanner(System.in);
        do {
            logs.add(answer);
            System.out.println(answer);
            current = scanner.nextLine();
            logs.add(String.format("%s %s", "User:", current));
            if (STOP.equals(current)) {
                while (!CONTINUE.equals(current)) {
                    current = scanner.nextLine();
                    logs.add(String.format("%s %s", "User:", current));
                    if (OUT.equals(current)) {
                        return;
                    }
                }
            }
            answer = String.format("%s %s", "Console:", phrases.get(random.nextInt(phrases.size() - 1)));
        } while (!OUT.equals(current));
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            phrases = in.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(str -> {
                try {
                    out.write(str + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkPath(String path) {
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("File with bot answers does not exist");
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("B:/log.txt", "B:/answers.txt");
        checkPath(cc.botAnswers);
        cc.run();
    }
}