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

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
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
        container = Arrays.copyOf(container, container.length != 0 ? container.length * 2 : 1);
    }

    @Override
    public T set(int index, T newValue) {
        T removable = get(index);
        container[index] = newValue;
        modCount++;
        return removable;
    }

    @Override
    public T remove(int index) {
        T removable = get(index);
        System.arraycopy(Arrays.copyOf(container, container.length), index + 1, container, index, container.length - index - 1);
        size--;
        modCount++;
        container[container.length - 1] = null;
        return removable;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
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
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }

        };
    }
}