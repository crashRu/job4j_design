package ru.job4j.io.testig;

import java.io.*;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void writeDataInFile(String path, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./src/data/botAnswers.txt";
        UsageEncoding encoding = new UsageEncoding();
        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }
}