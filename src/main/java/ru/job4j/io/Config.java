package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            values = in.lines()
                    .map(String::trim)
                    .filter(this::lineValidate)
                    .collect(Collectors.toMap(
                            line -> line.substring(0, line.indexOf("=")),
                            line -> line.substring(line.indexOf("=") + 1, line.length()),
                            (key, value) -> value, HashMap::new));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean lineValidate(String line) {
        int tempIndex = line.indexOf("=");
        boolean rsl = false;
        if (!line.isEmpty() && !line.startsWith("#")) {
            if (line.startsWith("=") || tempIndex == line.length() - 1 || !line.contains("=")) {
                throw new IllegalArgumentException(String.format("The line created an exceptional event, line: %s", line));
            } else if (line.substring(0, tempIndex).length() != 0
                    && line.substring(tempIndex, line.length() - 1).length() != 0) {
                rsl = true;
            }
        }
        return rsl;
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
}