
package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private E[] container;
    private int modCount;
    private int size;

    public SimpleLinkedList(int capacity) {
        this.container = (E[]) new Object[capacity];
        expandList();
    }

    @Override
    public void add(E value) {
        expandList();
        container[size++] = value;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E tempElement = container[0];
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                tempElement = container[i];
            }
        }
        return tempElement;
    }

    private void expandList() {
        if (size >= container.length - 1) {
            container = Arrays.copyOf(container, container.length * 2 + 1);
        }
    }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<>() {
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
