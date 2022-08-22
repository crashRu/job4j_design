
package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int modCount = 0;

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
        modCount++;
        tail.next = node;
    }

    @Override
    public E get(int index) {
        SimpleLinkedList.Node<E> tail = head;
        for (int i = 0; i < index; i++) {
            if (tail.next != null) {
                tail = tail.next;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        return tail.value;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            SimpleLinkedList.Node<E> node = head;
            private int tempMod = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (modCount != tempMod) {
                    throw new ConcurrentModificationException();
                } else if (!hasNext()) {
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
