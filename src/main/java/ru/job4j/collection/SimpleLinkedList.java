
package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private E[] container;
    private int modCount = 0;
    private int size = 0;

    public SimpleLinkedList(int capacity) {
        expandList();
        this.container = (E[]) new Object[capacity];
    }

    @Override
    public void add(E value) {
        container[size++] = value;
    }

    @Override
    public E get(int index) {
        return null;
    }

    private void expandList() {
        if (size >= container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2 + 1);
        }
    }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {
                private int index = 0;
                private int tempMod = modCount;

                @Override
                public boolean hasNext() {
                    if (tempMod != modCount) {
                        throw new ConcurrentModificationException();
                    }
                    return index < modCount;
                }

                @Override
                public E next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    } else {
                        return container[index++];
                    }
                }

            };
        }
    }
