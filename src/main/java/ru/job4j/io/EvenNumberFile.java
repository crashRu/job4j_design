package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFile {
    public static void main(String[] args) {
        parseByte(readFile("even.txt"))
                .forEach(number -> System.out.println(number + " " + (number % 2 == 0)));
    }

    public static byte[] readFile(String patch) {
        byte[] buffer = null;
        try (FileInputStream in = new FileInputStream(patch)) {
            buffer = new byte[in.available()];
            in.read(buffer, 0, in.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static List<Integer> parseByte(byte[] bytesList) {
        String result = "";
        for (int index = 0; index < bytesList.length; index++) {
            result = result + (char) bytesList[index];
        }
        try {
            return Arrays.stream(result.split("\n"))
                    .map(line -> Integer.parseInt(line))
                    .collect(Collectors.toList());
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}