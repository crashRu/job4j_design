package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static Map<FileProperty, List<Path>> fileList = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tempKey = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!fileList.containsKey(tempKey)) {
            fileList.put(tempKey, new ArrayList<>());
            fileList.get(tempKey).add(file);
        } else {
            fileList.get(tempKey).add(file);
        }
        return super.visitFile(file, attrs);
    }

    public static void printDuplicate() {
        fileList.keySet().stream()
                .filter(p -> fileList.get(p).size() > 1)
                .forEach(p -> System.out.println(fileList.get(p)));
    }
}