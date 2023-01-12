package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final StringBuilder CHAT_LOG = new StringBuilder();
    private List<String> list;
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        list = readPhrases();
    }

    public void run() {
        try (BufferedReader readUser = new BufferedReader(new InputStreamReader(System.in))) {
            String botStop = "Бот : Я буду вас ждать, как вернетесь введите команду \"Продолжить\"";
            String botContinue = "Бот : Жду вашего вопроса";

            System.out.printf("\tИнструкция по использованию чата\nЧто бы закончить общение напишите \"Закончить\"\n"
                    + "Что бы приостановить общение напишите \"Стоп\"\nЧто бы продолжить общение \"Продолжить\"\n");
            for (String userAnswer = readUser.readLine(); !(userAnswer).equals(OUT);) {
                CHAT_LOG.append(userAnswer + System.lineSeparator());
                String botAnswer = getBotAnswers();
                if (userAnswer.equals(STOP)) {
                    System.out.println(botStop);
                    CHAT_LOG.append(botStop);
                } else if (userAnswer.equals(CONTINUE)) {
                    System.out.println(botContinue);
                    CHAT_LOG.append(botContinue + System.lineSeparator());
                } else {
                    System.out.println("Бот : " + botAnswer);
                    CHAT_LOG.append("Бот : " + botAnswer + System.lineSeparator());
                }
            }
            saveLog(CHAT_LOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getBotAnswers() {
        return list.get((int) (Math.random() * list.size()));
    }

    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            list = reader.lines().collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    private void saveLog(StringBuilder logBuilder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("D:\\job4j\\job4j_design\\src\\data\\log.txt",
                "D:\\job4j\\job4j_design\\src\\data\\botAnswers.txt");
        cc.run();
    }
}