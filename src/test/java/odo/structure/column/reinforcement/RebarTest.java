package odo.structure.column.reinforcement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RebarTest {

    private static final double fy = 350;
    private static final double As = 387.1;

    @ParameterizedTest
    @CsvSource({"-87, 187", "87, 187", "-87, -187", "87, -187"})
    void constructorTest(double x, double y) {
        Rebar rebar = new Rebar(fy, As, x, y);
        assertThat(rebar.fy).isEqualTo(fy);
        assertThat(rebar.As).isEqualTo(As);
        assertThat(rebar.x).isEqualTo(x);
        assertThat(rebar.y).isEqualTo(y);
    }

}