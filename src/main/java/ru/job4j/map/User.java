package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }
}
