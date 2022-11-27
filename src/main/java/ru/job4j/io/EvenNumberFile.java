package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            byte[] buffer = new byte[in.available()];
            in.read(buffer, 0, in.available());
            String a = "";
            for (int i = 0; i < buffer.length; i++) {
                 a = a + (char) buffer[i];
            }
            String[] b = a.split("\n");
            for (String test : b) {
                System.out.println(test);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}