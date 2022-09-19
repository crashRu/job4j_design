package ru.job4j.set;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {
    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Ignore
    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }
    @Test
    void whenAddSetElements() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(0)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set.add(4)).isTrue();
        assertThat(set.add(5)).isTrue();
        assertThat(set.add(6)).isTrue();
        assertThat(set.add(5)).isFalse();
        assertThat(set.add(5)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }
}