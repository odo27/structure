package odo.structure.column.reinforcement;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RebarTest {

    @Test
    void constructorTest() {
        Rebar tensionBar = new Rebar(350, 774.2, 437);
        Rebar compressionBar = new Rebar(350, 774.2, 63);

        assertThat(tensionBar.fy).isEqualTo(350);
        assertThat(tensionBar.As).isEqualTo(774.2);
        assertThat(tensionBar.d).isEqualTo(437);
        assertThat(compressionBar.d).isEqualTo(63);
    }

}