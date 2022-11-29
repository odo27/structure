package odo.structure.column.section;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleTest {

    @Test
    void constructorTest() {
        Rectangle section = new Rectangle(300, 500);
        assertThat(section.b).isEqualTo(300);
        assertThat(section.h).isEqualTo(500);
    }

}
