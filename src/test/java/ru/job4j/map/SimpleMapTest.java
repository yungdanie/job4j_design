package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutObjectThenTrue() {
        SimpleMap<User, User> map = new SimpleMap<>();
        User user1 = new User("A", 12);
        assertTrue(map.put(user1, new User("B", 13)));
    }

    @Test
    public void whenPutObjectsThenGet() {
        SimpleMap<User, User> map = new SimpleMap<>();
        User user1 = new User("A", 12);
        User user2 = new User("B", 10);
        map.put(user1, user2);
        map.put(user2, user1);
        assertThat(map.get(user1), is(user2));
        assertThat(map.get(user2), is(user1));
    }

    @Test
    public void whenPutObjectThenRemove() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        map.put(obj2, obj1);
        assertThat(map.get(obj2), is(obj1));
        map.remove(obj2);
        assertNull(map.get(obj2));
    }

    @Test
    public void whenPutObjectsThenRemoveThenGet() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        map.put(obj2, obj1);
        map.put(obj1, obj2);
        assertThat(map.get(obj2), is(obj1));
        map.remove(obj2);
        assertNull(map.get(obj2));
        assertThat(map.get(obj1), is(obj2));
    }

    @Test
    public void whenIterator() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        map.put(obj2, obj1);
        map.put(obj1, obj2);
        Iterator<Object> iterator = map.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenIteratorThenHasNext() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Iterator<Object> iterator = map.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenIteratorThenGet() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        map.put(obj1, obj1);
        assertThat(map.get(obj1), is(obj1));
        Iterator<Object> iterator = map.iterator();
        assertThat(iterator.next(), is(obj1));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThenMod() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        map.put(obj1, obj1);
        Iterator<Object> iterator = map.iterator();
        map.put(obj1, obj1);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorThenGetAndGet() {
        SimpleMap<Object, Object> map = new SimpleMap<>();
        Object obj1 = new Object();
        map.put(obj1, obj1);
        Iterator<Object> iterator = map.iterator();
        iterator.next();
        iterator.next();
    }
}