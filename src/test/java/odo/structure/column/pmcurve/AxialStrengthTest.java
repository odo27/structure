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

    private Rectangle section;
    private Concrete concrete;
    private List<Rebar> rebars;

    @BeforeEach
    void setUp() {
        section = new Rectangle(300, 500);
        concrete = new Concrete(24);

        Rebar tensionBar = new Rebar(350, 774.2, 437);
        Rebar compressionBar = new Rebar(350, 774.2, 63);

        rebars = List.of(tensionBar, compressionBar);
    }

    @ParameterizedTest
    @CsvSource({"460, 2687116", "276, 1435752", "130, 644696.4615"})
    void calculateTest(double c, double expected) {
        assertThat(AxialStrength.calculate(section, concrete, rebars, c)).isEqualTo(expected, withPrecision(1e-4));
    }

}
