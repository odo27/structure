package odo.structure.analysis.section;

import odo.structure.column.Column;
import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class BiAxialTest {

    private Column column;

    @BeforeEach
    void setUp() {
        Rectangle section = new Rectangle(400, 400);
        Concrete concrete = new Concrete(21);
        List<Rebar> rebars = List.of(
                new Rebar(400, 506, -150, 150),
                new Rebar(400, 506, 150, -150)
        );
        column = new Column(section, concrete, rebars);
    }

    @Test
    void PMCurveTest() {
        assertThatCode(() -> new BiAxial(column).PMCurve(59.71204))
                .doesNotThrowAnyException();
    }

    @Test
    void reducedPMCurveTest() {
        assertThatCode(() -> new BiAxial(column).reducedPMCurve(59.71204))
                .doesNotThrowAnyException();
    }

}
