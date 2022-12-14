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
                    .map(line -> {
                        String[] s = line.split("=");
                        if (!line.startsWith("#") && !line.isEmpty()
                                && (line.indexOf("=") == 0 || s.length < 2
                                        || s[0].length() == 0 || s[1].length() == 0)) {
                            throw new IllegalArgumentException();
                        }
                        return line;
                    })
                    .filter(line -> line.indexOf('=') > 0)
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