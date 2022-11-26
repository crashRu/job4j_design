package ru.job4j.inputOutput;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Matrix {
    public static void main(String[] args) {
        multiple(30);
    }
    public static int[][] multiple(int size) {
        int[][] arrays = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                arrays[row][cell] = (cell + 1) * (row + 1);
            }
        }
        fileOutput(arrays);
        return arrays;
    }

    public static void fileOutput(int[][] arrays) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int[] a : arrays) {
                for (int b : a) {
                    out.write(Integer.toString(b).getBytes(StandardCharsets.UTF_8));
                    out.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
