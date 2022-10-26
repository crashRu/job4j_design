package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = indexFor(hash((key != null) ? key.hashCode() : 0));
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            count++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }


    private int indexFor(int hash) {
        return hash & (capacity);
    }

    private void expand() {
        if ((float)count / ((float)capacity * 2) >= LOAD_FACTOR) {
            MapEntry<K, V>[] tempValue = new MapEntry[table.length * 2];
            for (int i = 0; i < table.length; i++) {
                tempValue[indexFor(table[i].key.hashCode())] = table[i];
            }
            table = tempValue;
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash((key != null) ? key.hashCode() : 0));
        if (table[index] != null) {
            Object tempTableKey = table[index].key == null ? 0 : table[index].key;
            Object tempKey = key == null ? 0 : key;
            if (table[index] != null && hash(tempTableKey.hashCode()) == hash(tempKey.hashCode())
                    && (tempTableKey).equals(tempKey)) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean tempValue = false;
        int index = indexFor(hash((key != null) ? key.hashCode() : 0));
        if (get(key) != null) {
            table[index] = null;
            count--;
            tempValue = true;
        }
        return tempValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index = 0;
            private int tempModCount = count;
            private int tempCount = count;

            @Override
            public boolean hasNext() {
                if (count != tempModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < tempCount;
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