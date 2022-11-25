package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int addUserCount = 0;
        int changeUserCount = 0;
        int removeUserCount = 0;

        Map<Integer, String> currMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        Map<Integer, String> prevMap = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        for (User prev : previous) {
            if (currMap.containsKey(prev.getId())
                    && !prev.getName().equals(currMap.get(prev.getId()))) {
                changeUserCount++;
            }
        }

        for (User user : current) {
            if (!prevMap.containsKey(user.getId())) {
                addUserCount++;
            }
        }

        for (User user : previous) {
            if (!currMap.containsKey(user.getId())) {
                removeUserCount++;
            }
        }
        return new Info(addUserCount, changeUserCount, removeUserCount);
    }
}