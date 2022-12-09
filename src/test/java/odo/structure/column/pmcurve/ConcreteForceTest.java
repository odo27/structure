package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ConcreteForceTest {

    private Concrete concrete;

    @BeforeEach
    void setUp() {
        concrete = new Concrete(24);
    }

    @ParameterizedTest
    @CsvSource({"30, 45, 3, 13200", "25, 30, 5, 12470.76581"})
    void calculateTest(double c, double theta, double n, double expected) {
        assertThat(ConcreteForce.calculate(concrete, c, theta, n)).isEqualTo(expected, withPrecision(1e-5));
    }

}