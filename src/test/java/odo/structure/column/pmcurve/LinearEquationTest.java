package odo.structure.column.pmcurve;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LinearEquationTest {

    private LinearEquation linearEquation;

    @Test
    void constructorTest() {
        assertThatCode(() -> linearEquation = new LinearEquation(3, 5, 10))
                .doesNotThrowAnyException();
        assertThat(linearEquation.cx).isEqualTo(3);
        assertThat(linearEquation.cy).isEqualTo(5);
        assertThat(linearEquation.c).isEqualTo(10);
    }

}