package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class PureStrengthTest {

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

    @Test
    void compressionTest() {
        assertThat(PureStrength.compression(section, concrete, rebars)).isEqualTo(3570352.640, withPrecision(1e-3));
    }

    @Test
    void tensionTest() {
        assertThat(PureStrength.tension(rebars)).isEqualTo(-541940, withPrecision(1e-5));
    }

}
