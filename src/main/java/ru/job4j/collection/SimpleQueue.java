package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private T rsl;

    public T poll() {
        return out.pop();
    }

    public void push(T value) {
        while (out.size() > 0) {
            in.push(out.pop());
        }
        out.push(value);
        while (in.size() > 0) {
            out.push(in.pop());
        }
     }
}