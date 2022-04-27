package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenCreateUnavailable() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.csv");
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.write("400 13:12:01" + System.lineSeparator());
            out.write("500 13:12:03" + System.lineSeparator());
            out.write("300 13:12:05" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder string = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(string::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(string.toString(), is("13:12:01;13:12:05;"));
    }

}