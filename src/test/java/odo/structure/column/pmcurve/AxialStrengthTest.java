package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class AxialStrengthTest {

    private static final double fy = 350;
    private static final double As = 387.1;
    private static final double x = 87;
    private static final double y = 187;

    private Rectangle section;
    private Concrete concrete;
    private List<Rebar> rebars;

    @BeforeEach
    void setUp() {
        section = new Rectangle(300, 500);
        concrete = new Concrete(24);

        Rebar leftTensionBar = new Rebar(fy, As, -x, -y);
        Rebar rightTensionBar = new Rebar(fy, As, x, -y);
        Rebar leftCompressionBar = new Rebar(fy, As, -x, y);
        Rebar rightCompressionBar = new Rebar(fy, As, x, y);

        rebars = List.of(leftTensionBar, rightTensionBar, leftCompressionBar, rightCompressionBar);
    }

    @ParameterizedTest
    @CsvSource({"460, 2687116", "276, 1435752", "130, 644696.4615"})
    void calculateTest(double c, double expected) {
        assertThat(AxialStrength.calculate(section, concrete, rebars, c)).isEqualTo(expected, withPrecision(1e-4));
    }

    @ParameterizedTest
    @CsvSource({"460, 2392920"})
    void concreteForceTest(double c, double expected) {
        assertThat(AxialStrength.concreteForce(section, concrete, c)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0, 460, -11613", "1, 460, -11613", "2, 460, -135485", "3, 460, -135485"})
    void steelForceTest(int index, double c, double expected) {
        assertThat(AxialStrength.steelForce(section, rebars.get(index), c)).isEqualTo(expected, withPrecision(1e-5));
    }

}
