package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int addUserInfo = 0;
        int removeUserInfo = 0;
        int changeUserInfo;

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