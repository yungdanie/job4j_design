package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        Optional<T> rsl = Optional.ofNullable(storage.replace(id, model));
        return rsl.isPresent();
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id, storage.get(id));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}