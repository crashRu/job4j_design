package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Objects;

public class Worker {
    String[] responsibilities;
    Position position;
    String name;
    int age;
    boolean sex;

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Worker work = new Worker(new String[]{"perseverance", "Attentiveness"}, Position.DIRECTOR, "Jack", 37, true);
        String json = gson.toJson(work);
        System.out.println(json);
        var obj = gson.fromJson(json, Worker.class);
        System.out.println(obj);
    }

    public Worker(String[] responsibilities, Position position, String name, int age, boolean sex) {
        responsibilities = responsibilities;
        this.position = position;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String[] getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String[] responsibilities) {
        responsibilities = responsibilities;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Worker{"
               + "Responsibilities=" + Arrays.toString(responsibilities)
               + ", position=" + position
               + ", name='" + name + '\''
               + ", age=" + age
               + ", sex=" + sex
               + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return age == worker.age && sex == worker.sex && Arrays.equals(responsibilities, worker.responsibilities) && position == worker.position && Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(position, name, age, sex);
        result = 31 * result + Arrays.hashCode(responsibilities);
        return result;
    }

}
