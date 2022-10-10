package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private final int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        boolean result = true;
        if (key != null) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null && table[i].hashCode() == key.hashCode()
                        && table[i].equals(key)) {
                    result = false;
                }
            }
            if (result) {
              int index =  indexFor(key.hashCode());
                table[index] = new MapEntry(key, value);
                count++;
            }
        }
        return true;
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    private int hash(Object hashCode) {
        int h = hashCode();
        return (hashCode == null) ? 0 : (h) ^ (h >>> 16);
    }

    private int indexFor(int hash) {
        return hash(hash) & (table.length - 1);
    }

    private void expand() {
        if (count / capacity >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempValue = new MapEntry[table.length * 2];
            for (int i = 0; i < table.length; i++) {
                tempValue[i] = table[i];
            }
            table = tempValue;
        }
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < table.length; i++) {
            if (key != null && table[i] != null && table[i].key.equals(key)) {
                return (V) table[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean tempValue = false;
        for (int i = 0; i < table.length; i++) {
            if (key != null && table[i] != null && table[i].key.equals(key)) {
                table[i] = null;
                tempValue = true;
                count--;
                break;
            }
        }
        return tempValue;
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
        };
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