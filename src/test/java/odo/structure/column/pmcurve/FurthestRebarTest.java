package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FurthestRebarTest {

    @Test
    void calculateTest() {
        List<Rebar> rebars = List.of(
                new Rebar(400, 774.2, 100,300),
                new Rebar(400, 774.2, 100, 200),
                new Rebar(400, 774.2, 100, 100),
                new Rebar(400, 774.2, 100, 0),
                new Rebar(400, 774.2, 100, -100)
        );
        Rebar furthestRebar = FurthestRebar.calculate(rebars);
        assertThat(furthestRebar.y).isEqualTo(-100);
    }

}
