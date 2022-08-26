package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
        //Удаление и возврат удаленного значения
    }

    public void push(T value) {
        linked.addFirst(value);
        //Добавляет элемент в колекцию
    }
}