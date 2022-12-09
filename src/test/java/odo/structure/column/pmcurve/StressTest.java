package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class StressTest {

    private static final double fy = 350;
    private static final double As = 387.1;
    private static final double x = 87;
    private static final double y = 187;

    private Rectangle section;

    private Rebar leftTensionBar;
    private Rebar rightTensionBar;
    private Rebar leftCompressionBar;
    private Rebar rightCompressionBar;

    @BeforeEach
    void setUp() {
        section = new Rectangle(300, 500);

        leftTensionBar = new Rebar(fy, As, -x, -y);
        rightTensionBar = new Rebar(fy, As, x, -y);
        leftCompressionBar = new Rebar(fy, As, -x, y);
        rightCompressionBar = new Rebar(fy, As, x, y);
    }

    @ParameterizedTest
    @CsvSource({"276", "250", "130", "13"})
    void tensionBarYieldTest(double c) {
        assertThat(Stress.calculate(section, leftTensionBar, c)).isEqualTo(leftTensionBar.fy);
    }

    @ParameterizedTest
    @CsvSource({"300,274", "400,55.5", "436,1.37614", "460,-30"})
    void tensionBarStressTest(double c, double expected) {
        assertThat(Stress.calculate(section, rightTensionBar, c)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"160", "276", "460"})
    void compressionBarYieldTest(double c) {
        assertThat(Stress.calculate(section, leftCompressionBar, c)).isEqualTo(-leftCompressionBar.fy);
    }

    @ParameterizedTest
    @CsvSource({"50,156", "90,-180", "130,-309.23076"})
    void compressionBarStressTest(double c, double expected) {
        assertThat(Stress.calculate(section, rightCompressionBar, c)).isEqualTo(expected, withPrecision(1e-5));
    }

}
