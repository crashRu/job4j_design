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
        return new Info(elementCount(x -> !prevMap.containsKey(x.getId()), current),
                elementCount(x -> currMap.containsKey(x.getId())
                        && !(x.getName().equals(currMap.get(x.getId()))), previous),
                elementCount(x -> !currMap.containsKey(x.getId()), previous));
    }

    public static int elementCount(Predicate<? super User> pred, Set<User> set) {
        return (int) set.stream()
                .filter(pred)
                .count();
    }
}