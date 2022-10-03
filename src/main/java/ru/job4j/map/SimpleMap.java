package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {

    }

    private int hash(Object hashCode) {
        int h;
        return (hashCode == null) ? 0 : (h = hashCode.hashCode()) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash(hash) & (table.length - 1);
    }

    private void expand() {

    }

    private void reSize() {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int tempModCount = modCount;
            @Override
            public boolean hasNext() {
                if (modCount != tempModCount) {
                    throw new ConcurrentModificationException();
                }
                return index < modCount;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        }


        return null;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}