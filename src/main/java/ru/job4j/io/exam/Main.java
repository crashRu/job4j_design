package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.job4j.io.Search.search;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length  != 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        valid(args, argsName);
        fileWrite(dateProcess(argsName), argsName.get("o"));
    }

    private static List<Path> dateProcess(ArgsName argsName) throws IOException {
        List<Path> rsl = new ArrayList<>();
        String tArgument = argsName.get("t");
        String nArgument = argsName.get("n");
        Path dArgument = Path.of(argsName.get("d"));
        if (tArgument.equals("regex")) {
            rsl = search(dArgument, a -> a.getFileName().toString().matches(nArgument));
        } else if (tArgument.equals("mask")) {
            rsl = search(dArgument, a -> a.getFileName().toString().endsWith(nArgument));
        } else if (tArgument.equals("name")) {
            rsl = search(dArgument, a -> a.getFileName().toString().equals(nArgument));
        }
        return rsl;
    }

    private static void fileWrite(List<Path> rsl, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + fileName))) {

            for (Path result : rsl) {
                writer.write(result.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void valid(String[] args, ArgsName arguments) {
        File directory = Path.of(arguments.get("d")).toFile();
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", args[0]));
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s - not directory", args[0]));
        }
    }
}
