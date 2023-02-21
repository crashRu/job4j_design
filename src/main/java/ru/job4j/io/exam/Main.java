package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ru.job4j.io.Search.search;

public class Main {
    public static void main(String[] args) throws IOException {
        args = new String[]{"-d=C:\\Users\\User\\Desktop", "-n=txt", "-t=mask", "-o=log.txt"};
        if (args.length  != 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        valid(args, argsName);
    }
    
    private static void valid(String[] args, ArgsName arguments) {
        File directory = Path.of(arguments.get("d")).toFile();
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", args[0]));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - not directory", args[0]));
        }
        if (!arguments.get("n").startsWith("\\w+") || !arguments.get("t").startsWith("\\w+")) {
            throw new IllegalArgumentException("This search argument is not extension.");
        }
        if (!arguments.get("o").endsWith("\\.\\w+")) {
            throw new IllegalArgumentException("Argument must be in .zip format");
        }
        System.out.println("valid complete!");
    }
}
