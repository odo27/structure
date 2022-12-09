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

class FlexuralStrengthTest {

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

        Rebar leftTensionBar = new Rebar(fy, As, -x, y);
        Rebar rightTensionBar = new Rebar(fy, As, x, y);
        Rebar leftCompressionBar = new Rebar(fy, As, -x, -y);
        Rebar rightCompressionBar = new Rebar(fy, As, x, -y);

        rebars = List.of(leftTensionBar, rightTensionBar, leftCompressionBar, rightCompressionBar);
    }

    @ParameterizedTest
    @CsvSource({"460, 176742268", "276, 291867070.4", "130, 227142033.3"})
    void calculateTest(double c, double expected) {
        assertThat(FlexuralStrength.calculate(section, concrete, rebars, c)).isEqualTo(expected, withPrecision(1e-1));
    }

}
