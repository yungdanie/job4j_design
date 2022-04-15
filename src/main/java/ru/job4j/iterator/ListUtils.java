package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.add(value);
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        return list.stream()
                .filter(filter.negate())
                .collect(Collectors.toList());
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        return list.stream()
                .map(x -> x = filter.test(x) ? value : x)
                .collect(Collectors.toList());
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        return list.stream().filter(x -> elements
                .stream()
                .noneMatch(y -> y.equals(x)))
                .collect(Collectors.toList());
    }
}