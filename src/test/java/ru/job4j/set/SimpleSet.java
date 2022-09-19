package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.stream.Stream;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean result = !contains(value);
        if (result) {
            set.add(value);
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T tempValue : set) {
            result = tempValue.equals(null) || tempValue.equals(value);
        }
        /*
         return Stream.of(set).anyMatch(s -> s.equals(value));
        */
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();

    }
}