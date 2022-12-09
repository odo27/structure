package odo.structure.column;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class ColumnTest {

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

    @Test
    void constructorTest() {
        assertThatCode(() -> new Column(section, concrete, rebars))
                .doesNotThrowAnyException();
    }

    @Test
    void PMCurveTest() {
        assertThatCode(() -> new Column(section, concrete, rebars).PMCurve())
                .doesNotThrowAnyException();
    }

    @Test
    void reducedPMCurveTest() {
        assertThatCode(() -> new Column(section, concrete, rebars).reducedPMCurve())
                .doesNotThrowAnyException();
    }

}
