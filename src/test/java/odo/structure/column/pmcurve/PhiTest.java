package odo.structure.column.pmcurve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PhiTest {

    private double fy;

    @BeforeEach
    void setUp() {
        fy = 350;
    }

    @ParameterizedTest
    @CsvSource({"0.001", "0.0015"})
    void compressionDominantSectionTest(double et) {
        assertThat(Phi.calculate(et, fy)).isEqualTo(0.65);
    }

    @ParameterizedTest
    @CsvSource({"0.002, 0.66538", "0.003, 0.72692", "0.004, 0.78846"})
    void transitionSectionTest(double et, double expected) {
        assertThat(Phi.calculate(et, fy)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"0.005", "0.006", "0.007"})
    void tensionDominantSectionTest(double et) {
        assertThat(Phi.calculate(et, fy)).isEqualTo(0.85);
    }

}
