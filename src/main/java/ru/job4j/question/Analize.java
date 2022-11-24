package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int addUserInfo = 0;
        int removeUserInfo = 0;
        int changeUserInfo;

        changeUserInfo = previous.stream()
                .collect(Collectors.toMap(prev -> prev.getId(), prev -> prev.getName()))
                .containsKey(current.contains())

        for (User user : current) {
            if (!previous.contains(user)) {
                addUserInfo++;
            }
        }
        for (User user : previous) {
            if (!current.contains(user)) {
                removeUserInfo++;
            }
        }
        return new Info(addUserInfo,0,removeUserInfo);
    }
}