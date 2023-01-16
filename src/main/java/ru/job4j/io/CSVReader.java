package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static List<User> userList = new ArrayList<>();
    public static void handle(ArgsName argsName) {
        Path pathIn = Path.of(argsName.get("path"));
        Path pathOUT = Path.of(argsName.get("out"));
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(new FileInputStream(String.valueOf(pathIn))).useDelimiter(delimiter)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] user = scanner.nextLine().split(delimiter);
                userList.add(new User(user[0], Integer.parseInt(user[1]), user[2], user[3]));
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        writeFile(listFormation(argsName, delimiter), pathOUT);
    }

    private static StringBuilder listFormation(ArgsName argsName, String delimer) {
        String filter = argsName.get("filter");
        List<String> filterList = List.of(filter.split("\\,"));
        StringBuilder endListDateUser = new StringBuilder(filter + System.lineSeparator());
        for (int a = 0; a < userList.size(); a++) {
            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).equals("name")) {
                    endListDateUser.append(userList.get(a).getName() + delimer);
                }
                if (filterList.get(i).equals("age")) {
                    endListDateUser.append(userList.get(a).getAge() + delimer);
                }
                if (filterList.get(i).equals("last_name")) {
                    endListDateUser.append(userList.get(a).getLastName() + delimer);
                }
                if (filterList.get(i).equals("education")) {
                    endListDateUser.append(userList.get(a).getEducation() + delimer);
                }
            }
            endListDateUser.delete(endListDateUser.length() - 1, endListDateUser.length());
            endListDateUser.append(System.lineSeparator());
        }
        return endListDateUser;
    }

    private static void writeFile(StringBuilder builder, Path path) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path.toFile())))) {
            out.write(builder.toString());
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\job4j\\job4j_design\\src\\data\\text.csv");
        File target = new File("D:\\job4j\\job4j_design\\src\\data\\text.csv");
        handle(ArgsName.of(new String[]{"-path=" + file.getAbsolutePath(), "-delimiter=,",
                "-out=" + target.getAbsolutePath(), "-filter=education,age,last_name"}));
    }
}