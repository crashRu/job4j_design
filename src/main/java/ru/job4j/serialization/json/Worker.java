package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "worker")
@XmlAccessorType(XmlAccessType.FIELD)
public class Worker {
    @XmlAttribute
    private String[] responsibilities;
    private Position position;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean sex;

    public Worker() {
    }

    public Worker(String[] responsibilities, Position position, String name, int age, boolean sex) {
        this.responsibilities = responsibilities;
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
}
