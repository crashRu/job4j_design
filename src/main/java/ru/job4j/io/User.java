package ru.job4j.io;

import java.util.Objects;

public class User {
    String name;
    int age;
    String lastName;
    String education;

    public User(String name, int age, String lastName, String education) {
        this.name = name;
        this.age = age;
        this.lastName = lastName;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEducation() {
        return education;
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
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(education, user.education);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, lastName, education);
    }

    @Override
    public String toString() {
        return name + '\'' + age + "\'" + lastName + '\'' + education + '\'';
    }
}
