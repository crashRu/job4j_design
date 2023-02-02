package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;

import java.io.File;
import java.nio.file.Path;

public class WorkingWithArguments {

    private static String[] args;
    private static ArgsName argsName;
    private static String searchPath;
    private static String criteriaSearch;
    private static String typeSearch;
    private static String pathLogWriter;


    public WorkingWithArguments(String[] args, ArgsName argsName) {
        this.args = args;
        this.argsName = argsName;
        argsValid();
        searchPath = argsName.get("d");
        criteriaSearch = argsName.get("n");
        typeSearch = argsName.get("t");
        pathLogWriter = argsName.get("o");
    }

    private static void argsValid() {
        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        File directory = Path.of(argsName.get("d")).toFile();
        if (!directory.exists()) {
            throw new IllegalArgumentException(String.format("%s - not exist", args[0]));
        }
        if (directory.getFreeSpace() == 0) {
            throw new IllegalArgumentException(String.format("%s - directory is empty", args[0]));
        }
    }

    public static String getSearchPath() {
        return searchPath;
    }

    public static String getCriteriaSearch() {
        return criteriaSearch;
    }

    public static String getTypeSearch() {
        return typeSearch;
    }

    public static String getPathLogWriter() {
        return pathLogWriter;
    }
}
