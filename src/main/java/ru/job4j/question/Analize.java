package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> currMap = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> prevMap = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        return new Info(elementCount(prevUser -> !prevMap.containsKey(prevUser.getId()), current),
                elementCount(currUser -> currMap.containsKey(currUser.getId())
                        && !(currUser.getName().equals(currMap.get(currUser.getId()))), previous),
                elementCount(currUser -> !currMap.containsKey(currUser.getId()), previous));
    }

    public static int elementCount(Predicate<? super User> pred, Set<User> set) {
        return (int) set.stream()
                .filter(pred)
                .count();
    }
}