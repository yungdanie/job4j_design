package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddEqualElementsThenIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(3);
        set.add(3);
        set.add(4);
        Iterator<Integer> iterator = set.iterator();
        assertThat(iterator.next(), is(3));
        assertThat(iterator.next(), is(4));
    }

    @Test
    public void whenContains() {
        Set<Integer> set = new SimpleSet<>();
        set.add(3);
        set.add(3);
        assertThat(set.iterator().next(), is(3));
    }
}