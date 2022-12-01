package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class StressTest {

    private Rebar tensionBar;
    private Rebar compressionBar;

    @BeforeEach
    void setUp() {
        tensionBar = new Rebar(350, 774.2, 437);
        compressionBar = new Rebar(350, 774.2, 63);
    }

    @ParameterizedTest
    @CsvSource({"276", "250", "130", "13"})
    void tensionBarYieldTest(double c) {
        assertThat(Stress.calculate(tensionBar, c)).isEqualTo(tensionBar.fy);
    }

    @ParameterizedTest
    @CsvSource({"300,274", "400,55.5", "436,1.37614", "460,-30"})
    void tensionBarStressTest(double c, double expected) {
        assertThat(Stress.calculate(tensionBar, c)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"160", "276", "460"})
    void compressionBarYieldTest(double c) {
        assertThat(Stress.calculate(compressionBar, c)).isEqualTo(-compressionBar.fy);
    }

    @ParameterizedTest
    @CsvSource({"50,156", "90,-180", "130,-309.23076"})
    void compressionBarStressTest(double c, double expected) {
        assertThat(Stress.calculate(compressionBar, c)).isEqualTo(expected, withPrecision(1e-5));
    }

}
