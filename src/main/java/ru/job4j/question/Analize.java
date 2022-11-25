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
        return new Info(elementCount(currUser -> !prevMap.containsKey(currUser.getId()), current),
                elementCount(prevUser -> currMap.containsKey(prevUser.getId())
                        && !(prevUser.getName().equals(currMap.get(prevUser.getId()))), previous),
                elementCount(prevUser -> !currMap.containsKey(prevUser.getId()), previous));
    }

    public static int elementCount(Predicate<? super User> pred, Set<User> set) {
        return (int) set.stream()
                .filter(pred)
                .count();
    }
}