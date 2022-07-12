package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        T user = storage.replace(id, model);
        return user != null;
    }

    @Override
    public boolean delete(String id) {
        return storage.remove(id, findById(id));
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {

            return true;
        }
        if (o == null || getClass() != o.getClass()) {

            return false;
        }
        MemStore<?> memStore = (MemStore<?>) o;
        return storage.equals(memStore.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}