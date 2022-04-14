package ru.job4j.collection;

public class SimpleStack<T> {
    private int size;
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public int size() {
        return size;
    }
}