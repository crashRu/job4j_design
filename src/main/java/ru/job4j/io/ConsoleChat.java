package ru.job4j.io;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final StringBuilder builder = new StringBuilder();
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader readUser = new BufferedReader(new InputStreamReader(System.in))) {
            String answer;
            while (!(answer = readUser.readLine()).isEmpty()) {
                if (answer.equals(OUT)) {
                    readPhrases();
                    break;
                } else if (answer.equals(STOP)) {
                } else {
                    builder.append(answer);
                }
                answer = readUser.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(writer.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}