package odo.structure.column;

import odo.structure.column.material.Concrete;
import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class ColumnTest {

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
