package odo.structure.column.pmcurve;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrainTest {

    @Test
    void calculateTest() {
        assertThat(Strain.calculate(437, 276)).isEqualTo(0.00175);
    }

}
