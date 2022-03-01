package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }

    /**
     * Скомпонуйте код итератора таким образом, чтобы весь код из этой строки
     * return data[data.length - 1 - point++];
     * Был распределен по всему итератору и эта строка приняла такой вид:
     * return data[point--];
     */
}