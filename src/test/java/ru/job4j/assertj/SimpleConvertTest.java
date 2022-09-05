package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert conv = new SimpleConvert();
        List<String> array = conv.toList("first", "second", "three", "four");
        assertThat(array).contains("second")
                .allMatch(e -> e.length() > 3)
                .anySatisfy(e -> {
                    assertThat(e.length() < 10);
                    assertThat(e.startsWith("first"));
                });
    }

    @Test
    void checkToSet() {
        SimpleConvert conv = new SimpleConvert();
        Set<String> array = conv.toSet("First", "Second", "Three", "Four");
        assertThat(array)
                .isNotEmpty()
                .isNotNull()
                .contains("Three")
                .hasSize(4)
                .filteredOn(e -> e.length() <= 5
                        && e.startsWith("F"))
                .hasSize(2);
    }

    @Test
    void checkToMap() {
        SimpleConvert conv = new SimpleConvert();
        Map<Integer, String> map = Map.of(1, "first", 2, "second",
                                          3, "Three", 4, "four");
        assertThat(map).doesNotContainKey(0)
                .containsEntry(1, "first");
    }
}