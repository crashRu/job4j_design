package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    private static List<DataStore> list = new LinkedList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            list = reader.lines()
                    .filter(line -> !line.isEmpty())
                    .map(line -> new DataStore(line.substring(0, line.indexOf("\s")),
                            line.substring(line.indexOf("\s"))))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        healthHandler();
        writingFile(target);
    }

    public static void writingFile(String patch) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patch))) {
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void healthHandler() {
        boolean conditionServer = true;
        for (DataStore dat : list) {
            if (conditionServer && dat.code >= 400) {
                builder.append(dat.data + ";");
                conditionServer = false;
            } else if (!conditionServer && dat.code < 400) {
                builder.append(dat.data + ";" + System.lineSeparator());
                conditionServer = true;
            }
        }
    }

    public static void main(String[] args) {
        unavailable("unavailable.csv", "serverLog.csv");
    }
}

class DataStore {
    int code;
    String data;

    public DataStore(String code, String data) {
        this.code = Integer.parseInt(code);
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataStore{" +
                "code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
