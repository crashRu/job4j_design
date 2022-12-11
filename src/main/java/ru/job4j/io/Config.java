package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private static final String LINE_FILTER_REGX = "^[\\w\\d\\.\\_\\ˆ]+={1}[\\w\\d\\.\\_\\:\\/\\/]+$";
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
           values = in.lines()
                   .map(x -> examLine(x))
                    .filter(line -> line.matches(LINE_FILTER_REGX))
                    .map(line -> line.split("="))
                    .collect(Collectors.toMap(
                            list -> list[0],
                            list -> list[1],
                            (key, value) -> value, HashMap::new));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String examLine(String line) {
        if (line.trim().length() == 0 || line.matches("^(=\\w*|\\w+=| [^=])$")) {
            throw new IllegalArgumentException("Format line is not true");
        } else {
            return line;
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        new Config("app.properties").load();
    }

}