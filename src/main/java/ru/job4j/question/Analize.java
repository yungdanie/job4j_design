package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        if (previous.hashCode() == current.hashCode()
                && previous.equals(current)) {
            return info;
        }
        int add = 0;
        int del = 0;
        int chang = 0;
        HashMap<Integer, User> prevMap = new HashMap<>();
        HashMap<Integer, User> curMap = new HashMap<>();
        previous.forEach(x -> prevMap.put(x.getId(), x));
        current.forEach(x -> curMap.put(x.getId(), x));
        HashSet<User> set = new HashSet<>(previous);
        set.addAll(current);
        for (User next : set) {
            if (current.contains(next) && prevMap.get(next.getId()) == null) {
                info.setAdded(++add);
            } else if (current.contains(next) && !prevMap.get(next.getId()).getName().equals(next.getName())) {
                info.setChanged(++chang);
            } else if (previous.contains(next) && curMap.get(next.getId()) == null) {
                info.setDeleted(++del);
            }
        }
        return info;
    }
}
