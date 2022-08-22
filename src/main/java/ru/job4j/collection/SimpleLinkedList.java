
package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> head;

    @Override
    public void add(E value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
            return;
        }
        SimpleLinkedList.Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
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
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            SimpleLinkedList.Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        SimpleLinkedList.Node<T> next;

        public Node(T value, SimpleLinkedList.Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
    }
