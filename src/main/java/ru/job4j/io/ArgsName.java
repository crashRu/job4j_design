package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String tempKey = values.get(key);
        if (Objects.isNull(tempKey)) {
            throw new IllegalArgumentException("Options is not found ");
        }
        return tempKey;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Argument is not found");
        }
        for (String line : args) {
            Matcher matcher = Pattern.compile("^\\-(?<key>[\\w.]+)=(?<value>[A-z0-9-=?.:,;]+$)").matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Argument does not match pattern");
            }
            values.putIfAbsent(matcher.group("key"), matcher.group("value"));
        }

    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}