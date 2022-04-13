package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            expansion();
        }
            container[size++] = value;
            modCount++;
    }

    private void expansion() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T removable = container[index];
        container[index] = newValue;
        modCount++;
        return removable;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T removable = container[index];
        System.arraycopy(Arrays.copyOf(container, container.length), index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        container[container.length - 1] = null;
        return removable;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor;
            private final T[] data = container;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < size && data[cursor] == null) {
                    cursor++;
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return data[cursor++];
            }

        };
    }
}