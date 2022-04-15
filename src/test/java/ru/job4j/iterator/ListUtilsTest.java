package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.iterator.ListUtils.removeIf;

public class ListUtilsTest {

    @Test
    public void whenRemoveIf() {

    }

    @Test
    public void whenAddBefore() {

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        throw new IndexOutOfBoundsException();
    }

    @Test
    public void whenAddAfterLast() {

    }

}