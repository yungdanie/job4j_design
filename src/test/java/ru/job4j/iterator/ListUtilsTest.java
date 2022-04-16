package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.iterator.ListUtils.*;

public class ListUtilsTest {

    @Test
    public void whenRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 1, 1));
        removeIf(list, x -> x == 1);
        assertThat(list, is(List.of(2, 3)));
    }

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 3));
        replaceIf(list, x -> x == 1, 0);
        assertThat(list, is(List.of(0, 0, 0, 2, 3)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 3, 0));
        List<Integer> test = new ArrayList<>(Arrays.asList(1, 3, 4, 2, 0));
        removeAll(list, test);
        assertThat(list, is(List.of(5, 6)));
    }
}