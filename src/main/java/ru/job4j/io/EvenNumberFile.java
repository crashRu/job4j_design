package ru.job4j.io;

import javax.sound.midi.Patch;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFile {
    public static void main(String[] args) {
       parseByte(readFile("even.txt")).forEach(System.out::println);
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
        String[] a = result.split("\n");
        ArrayList list = new ArrayList();
        for (String b : a) {
            System.out.println(a.toString());
          list.add(Integer.parseInt(b));
            System.out.println(b);
        }

        return new ArrayList<>();
    }
}