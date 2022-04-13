package ru.job4j.collection.list;

import ru.job4j.collection.SimpleArrayList;

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
        if (index >= size) {
                throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            cursor = cursor.next;
        }
        modCount++;
        return cursor.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> data = first;
            Node<E> next;
            private int ind;
            private final int mod = modCount;

            @Override
            public boolean hasNext() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return ind < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                next = data;
                data = next.next;
                ind++;
                return next.item;
            }
        };
    }
}