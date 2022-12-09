package odo.structure.column.pmcurve;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class NeutralAxisTest {

    private NeutralAxis neutralAxis;

    @ParameterizedTest
    @CsvSource({"30, 45, 60", "50, 60, 115.47005"})
    void distanceOfTwoCrossingPointTest(double c, double theta, double expected) {
        neutralAxis = new NeutralAxis(c, theta);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }
}
