package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String log = null;
        ArgsName arguments = ArgsName.of(args);
        WorkingWithArguments arg = new WorkingWithArguments(args, arguments);




        try (BufferedWriter buff = new BufferedWriter(new FileWriter(arg.getPathLogWriter()))) {
            buff.write(log);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
