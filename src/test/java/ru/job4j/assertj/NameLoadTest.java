package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyName(){
        NameLoad name = new NameLoad();
        assertThatThrownBy(name::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkNotEarlyName() {
        NameLoad name = new NameLoad();
        assertThatThrownBy(()-> name.parse("Кирилл"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: Кирилл does not contain the symbol \"=\"");
    }

    @Test
    void checkStartLineEarlyByName(){
        NameLoad name = new NameLoad();
        assertThatThrownBy(() -> name.parse("=Кирилл"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkEndLineEarlyByName() {
        NameLoad name = new NameLoad();
        assertThatThrownBy(() -> name.parse("Кирилл="))
                .isInstanceOf(IllegalArgumentException.class);
    }
}