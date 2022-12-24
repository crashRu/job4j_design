package ru.job4j.io;

import java.io.*;
import java.util.stream.Collectors;

public class Analysis {

    public static void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean serverOn = true;
            while (reader.ready()) {
                String line = reader.readLine();
                if (line.isEmpty()) {
                    continue;
                }
                String writeLine = line.replaceAll("\\d{3}\\s", "") + ";";
                int codeIndex = Integer.parseInt(line.substring(0, line.indexOf("\s")));


                if (serverOn && codeIndex >= 400 || !serverOn && codeIndex < 400 ) {
                    serverOn = !(codeIndex >= 400);
                    writer.print(writeLine + (serverOn ? System.lineSeparator() :""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("unavailable.csv", "serverLog.csv");
    }
}
