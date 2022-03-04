package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (; index < data.length; index++) {
            if (data.length > index) {
                if (data[index] % 2 == 0) {
                    rsl = true;
                    break;
                }
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            new NoSuchElementException();
        } else {
            return data[index++];
        }
        return 0;
    }
    /**
     *  - it.next() - возвращают только четные числа. В этом примере - это 4 и 2.
     *  - it.hasNext() - возвращает true, только если в массиве есть
     *  четные перед указателем. Например, если мы дернем два раза метод next(),
     *  то указатель сместится на второй элемент. При вызове метода hasNext()
     *  - он вернет false, т.к. после указателя больше нет четных чисел.
     *
     */
}