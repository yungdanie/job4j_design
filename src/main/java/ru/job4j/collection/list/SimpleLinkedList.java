package ru.job4j.collection.list;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;
    private Node<E> last;
    private Node<E> first;
    private Node<E> cursor;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        if (first == null) {
            first = new Node<>(null, value, null);
            last = first;
        } else {
            cursor = last;
            last = new Node<>(cursor, value, null);
            cursor.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        cursor = first;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        return cursor.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> data = first;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return data != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = data.item;
                data = data.next;
                return rsl;
            }
        };
    }
}