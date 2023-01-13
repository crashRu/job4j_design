package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<String> filterList = List.of(argsName.get("filter").split("\\,"));
        Path pathIn = Path.of(argsName.get("path"));
        Path pathOUT = Path.of(argsName.get("out"));
        String delimiter = argsName.get("delimiter");

        try (Scanner scanner = new Scanner(new FileInputStream(String.valueOf(pathIn))).useDelimiter(delimiter)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File file = new File("/Users/crash/job4j_design/src/data/text.csv");
        File target = new File("/Users/crash/job4j_design/src/data/text.csv");
        handle(ArgsName.of("-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"));
    }
}
