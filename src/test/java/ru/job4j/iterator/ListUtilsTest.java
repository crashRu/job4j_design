package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf(){
        input.add(2);
        input.add(4);
        ListUtils.removeIf(input, filter -> filter % 2 != 0);
        assertThat(input).hasSize(2).contains(2, 4);
    }

    @Test
    void whenReplaceIf() {
        input.add(3);
        ListUtils.replaceIf(input, filter -> filter < 3, 10);
        assertThat(input).hasSize(3).contains(10, 3, 3);
    }

    @Test
    void whenDeleteAll() {
        ListUtils.removeAll(input, List.of(1, 3));
        assertThat(input).hasSize(0);
    }
}