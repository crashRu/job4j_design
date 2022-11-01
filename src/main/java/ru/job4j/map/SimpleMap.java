package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = basket(key);
        boolean result = false;
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            result = true;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }


    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if ((float) modCount / (capacity) >= LOAD_FACTOR) {
            capacity *= 2;
            MapEntry<K, V>[] tempValue = new MapEntry[capacity];
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    tempValue[basket(table[i].key)] = table[i];
                }
            }
            table = tempValue;
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = basket(key);
        if (table[index] != null) {
            if (table[index] != null && hash(Objects.hashCode(table[index].key))
                    == hash(Objects.hashCode(key))
                    && Objects.equals(table[index].key, key)) {
                result = table[index].value;
            }
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean tempValue = false;
        int index = basket(key);
        if (get(key) != null) {
            table[index] = null;
            modCount--;
            tempValue = true;
        }
        return tempValue;
    }

    private int basket(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int tempModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (modCount != tempModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < table.length;
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