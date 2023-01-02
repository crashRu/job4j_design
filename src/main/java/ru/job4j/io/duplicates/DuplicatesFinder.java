package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("C:\\Users\\User\\Desktop\\dsfdsf"), new DuplicatesVisitor());
        DuplicatesVisitor.printDuplicate();
    }
}