package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        checkLaunchOptions(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void checkLaunchOptions(String[] args) {
        Matcher matchesPath = Pattern.compile("^[A-Z]\\:\\\\").matcher(args[0]);
        Matcher matchesFile = Pattern.compile("[A-Za-z]{1,4}$").matcher(args[1]);
        if (args.length != 2) {
            throw new IllegalArgumentException("Not all parameters entered");
        }
        if (!matchesPath.find()) {
            throw new IllegalArgumentException("Path entered incorrectly");
        }
        if (!matchesFile.matches()) {
            throw new IllegalArgumentException("File extension is not correct");
        }
    }
}