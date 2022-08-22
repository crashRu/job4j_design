
package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private int modCount = 0;
    private int size = 0;

    @Override
    public void add(E value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        modCount++;
        size++;
        tail.next = node;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> tail = head;
        for (int i = 0; i < index; i++) {
                tail = tail.next;
        }

        return tail.value;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
          Node<E> node = head;
            private int tempMod = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != tempMod) {
                    throw new ConcurrentModificationException();
                }
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
       Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
    }
