package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private static List<FileProperty> fileList = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        fileList.add(new FileProperty(file.toFile().length(), file.getFileName().toString()));
        return super.visitFile(file, attrs);
    }

    public static void printDuplicate() {
        fileList.stream().filter(p -> !fileList.contains(p)).forEach(System.out::println);
    }
}