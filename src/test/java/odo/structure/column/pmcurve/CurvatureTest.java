package odo.structure.column.pmcurve;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class CurvatureTest {

    @Test
    void calculateTest() {
        assertThat(Curvature.calculate(276)).isEqualTo(1.08695e-5, withPrecision(1e-10));
    }

}
