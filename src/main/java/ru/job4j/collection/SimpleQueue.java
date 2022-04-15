package ru.job4j.collection;

import java.util.EmptyStackException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private T rsl;

    public T poll() {
        if (out.size() == 0) {
            if (in.size() == 0) {
                throw new EmptyStackException();
            }
            while (in.size() > 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
     }
}