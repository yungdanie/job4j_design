package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = get(key) == null;
        if (count * LOAD_FACTOR >= capacity) {
            expand();
        }
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        } else if (table[index].key == key) {
            table[index].value = value;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode & hashCode >>> 8;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> elem : oldTable) {
            if (elem != null) {
                put(elem.key, elem.value);
            }
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        return table[indexFor(hash(key.hashCode()))] != null
                ? table[indexFor(hash(key.hashCode()))].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = table[indexFor(hash(key.hashCode()))] != null;
        if (rsl) {
            table[indexFor(hash(key.hashCode()))] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int cursor;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}