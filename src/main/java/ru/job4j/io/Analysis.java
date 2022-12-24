package ru.job4j.io;

import java.io.*;
import java.util.stream.Collectors;

public class Analysis {

    public static void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .filter(line -> !line.isEmpty())
                    .map(line -> {
                        int subStringIndex = Integer.parseInt(line.substring(0, line.indexOf("\s")));
                        String subStringDate = line.substring(line.indexOf("\s"));
                        if (builder.length() % 2 != 0 && subStringIndex >= 400) {
                            builder.append(subStringDate + ";");
                        } else if (builder.length() % 2 == 0 && subStringIndex < 400) {
                            builder.append(subStringDate + ";");
                        }
                        return builder;
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(builder);
    }

    public static void main(String[] args) {
        unavailable("unavailable.csv", "serverLog.csv");
    }
}
