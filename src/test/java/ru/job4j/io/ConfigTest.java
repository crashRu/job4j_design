package ru.job4j.io;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenErrorLine() {
        String patch = "./errorData.properties";
        Config conf = new Config(patch);
        Assertions.assertThrows(IllegalArgumentException.class, () -> conf.load());
    }

    @Test
    void whenLineIsEmpty() {
        String patch = "./emptyLine.properties";
        Config conf = new Config(patch);
        conf.load();
        assertThat(conf.value("hibernate.connection.username")).isEqualTo("root");
    }
}