package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
        modCount++;

    }

    @Override
    public T set(int index, T newValue) {
        T tempElement = container[index];
        container[checkIndex(index)] = newValue;
        return tempElement;
    }

    @Override
    public T remove(int index) {
        T tempElement = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return tempElement;
    }

    @Override
    public T get(int index) {
        return container[checkIndex(index)];
    }

    private int checkIndex(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int tempMod = modCount;

            @Override
            public boolean hasNext() {
                return index < modCount;
            }

            @Override
            public T next() {
                if (tempMod != modCount) {
                    throw new ConcurrentModificationException();
                } else if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    return container[index++];
                }
            }

        };
    }
}
