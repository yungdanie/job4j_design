package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenSuccessfulLoadAndGetFileWithCommAndSpace() {
        Config config = new Config("./data/prop_with_comments_and_space.properties");
        config.load();
        assertThat(config.value("gorod"), is("spb"));
        assertThat(config.value("daniel"), is("kocheshkov"));
    }

    @Test
    public void whenSuccessfulLoadAndGetFileWithoutComm() {
        Config config = new Config("./data/prop_without_comments.properties");
        config.load();
        assertThat(config.value("gorod"), is("spb"));
        assertThat(config.value("daniel"), is("kocheshkov"));
        assertThat(config.value("univer"), is("gorniy"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenLoadAndIllegalArgumentExc() {
        Config config = new Config("./data/prop_with_illegal_pattern.properties");
        config.load();
        assertThat(config.value("kocheshkov"), is("daniel"));
    }

    @Test
    public void whenLoadFileWithFewSightOfEquals() {
        Config config = new Config("./data/prop_with_few=.properties");
        config.load();
        assertThat(config.value("daniel"), is("kocheshkov=1"));
    }
}