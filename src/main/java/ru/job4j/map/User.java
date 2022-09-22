package ru.job4j.map;

import java.util.Calendar;

public class User {
    private final String name;
    private final int age;
    private final int children;
    private final Calendar birthday;

    public User(String name, int age, int children, Calendar birthday) {
        this.name = name;
        this.age = age;
        this.children = children;
        this.birthday = birthday;
    }
}
