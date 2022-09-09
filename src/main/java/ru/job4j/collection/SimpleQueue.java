package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private int inSize = 0;
    private int outSize = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        isEmpty();
        T temp = null;
        if (inSize > 0) {
            inSize--;
            temp = in.pop();
        } else {
            for (int i = 0; i < outSize; i++) {
                in.push(out.pop());
                inSize++;
            }
            poll();
        }


        return temp;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }

    private void isEmpty() {
        if (inSize == 0 && outSize == 0) {
            throw new NoSuchElementException();
        }
    }


}
