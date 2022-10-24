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
        boolean result = false;
        if ((hash(key) == 0 && !contains(key))) {
            for (int i = 0; i < count; i++) {
                if (hash(table[i].key) == 0) {
                    result = true;
                    break;
                }
            }
        }
        if (!result && !contains(key)) {
            table[count] = new MapEntry(key, value);
            count++;
            result = true;
        }
        expand();
        return result;
    }

    public boolean contains(K key) {
        boolean result = false;
        if (hash(key) != 0) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null
                        && hash(table[i].key) == hash(key)
                        && table[i].key.equals(key)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

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
        V result = null;
        for (int i = 0; i < table.length; i++) {
            if (key != null && table[i] != null) {
                if (hash(key) == hash(table[i].key) && key.equals(table[i].key)) {
                    result = table[i].value;
                }
            }
        }
        return result;
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
            private int tempModCount = count;

            @Override
            public boolean hasNext() {
                if (count != tempModCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
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