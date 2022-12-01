package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaximumDistanceTest {

    @Test
    void calculateTest() {
        List<Rebar> rebars = List.of(
                new Rebar(400, 774.2, 100),
                new Rebar(400, 774.2, 200),
                new Rebar(400, 774.2, 300),
                new Rebar(400, 774.2, 400),
                new Rebar(400, 774.2, 500)
        );
        assertThat(MaximumDistance.calculate(rebars)).isEqualTo(500);
    }

}
