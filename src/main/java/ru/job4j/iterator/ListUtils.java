package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        addBefore(list, index + 1, value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        list = list.stream()
                .filter(filter).
                collect(Collectors.toList());
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
       list.stream().filter(filter).forEach(x -> x = value);
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {

    }
}