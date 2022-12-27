package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenServerLogCreate(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("unavailable.csv").toFile();
        File target = tempDir.resolve("serverLog.csv").toFile();
        StringBuilder builder = new StringBuilder();
        try (PrintWriter write = new PrintWriter(source)) {
            write.println("200 10:56:01");
            write.println("500 10:57:01");
            write.println("400 10:58:01");
            write.println("300 10:59:01");
            write.println("500 11:01:02");
            write.println("200 11:02:02");
        }
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(builder::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").isEqualTo(builder.toString());
    }

}