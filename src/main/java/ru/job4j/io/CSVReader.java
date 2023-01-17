package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private static List<User> userList;
    public static void handle(ArgsName argsName) {
        userList = new ArrayList<>();
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

    private static String listFormation(ArgsName argsName, String delimer) {
        String filter = argsName.get("filter");
        List<String> filterList = List.of(filter.split("\\,"));
        StringBuilder endListDateUser = new StringBuilder(filter + System.lineSeparator());
        for (int a = 0; a < userList.size(); a++) {
            for (int i = 0; i < filterList.size(); i++) {
                if (("name").equals(filterList.get(i))) {
                    endListDateUser.append(userList.get(a).getName() + delimer);
                }
                if ("age".equals(filterList.get(i))) {
                    endListDateUser.append(userList.get(a).getAge() + delimer);
                }
                if ("last_name".equals(filterList.get(i))) {
                    endListDateUser.append(userList.get(a).getLastName() + delimer);
                }
                if ("education".equals(filterList.get(i))) {
                    endListDateUser.append(userList.get(a).getEducation() + delimer);
                }
            }
            endListDateUser.delete(endListDateUser.length() - 1, endListDateUser.length());
            endListDateUser.append(System.lineSeparator());
        }
        return endListDateUser.toString().replaceAll("\\,", delimer);
    }

    private static void writeFile(String builder, Path path) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path.toFile())))) {
            out.write(builder.toString());
            out.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}