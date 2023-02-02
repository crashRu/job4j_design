package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.job4j.io.Search.search;

public class Main {
    public static void main(String[] args) {
        String log = "null;";
        args = new String[]{"-d=C:\\Users\\User\\Desktop", "-n=txt", "-t=mask", "-o=log.txt"};
        ArgsName arguments = ArgsName.of(args);
        WorkingWithArguments arg = new WorkingWithArguments(args, arguments);
        Path start = Paths.get(arg.getSearchPath());
        try {
            search(start, p -> p.toFile().getName().endsWith(arg.getCriteriaSearch())).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (BufferedWriter buff = new BufferedWriter(new FileWriter(arg.getPathLogWriter()))) {
            buff.write(log);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
