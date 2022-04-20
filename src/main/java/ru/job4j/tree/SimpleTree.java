package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E extends Comparable<E>> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> node = findBy(parent);
        boolean rsl = node.isPresent();
        if (rsl && node.get().children.isEmpty()) {
            node.get().children.add(new Node<>(child));
        } else if (rsl) {
            List<Node<E>> children = node.get().children;
            Node<E> max;
            if (children.size() > 1) {
                max = children.get(1);
            } else {
                max = children.get(0);
                children.add(new Node<>(null));
            }
            if (max.value.compareTo(child) > 0) {
                children.set(1, max);
                children.set(0, new Node<>(child));
            } else if (max.value.compareTo(child) == 0) {
                max.children.add(new Node<>(child));
            } else {
                children.set(0, max);
                children.set(1, new Node<>(child));
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}