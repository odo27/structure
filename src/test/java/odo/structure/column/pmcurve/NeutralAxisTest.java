package odo.structure.column.pmcurve;

import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class NeutralAxisTest {

    private NeutralAxis neutralAxis;

    @ParameterizedTest
    @CsvSource({"30, 45, 60", "50, 60, 115.47005"})
    void distanceOfTwoCrossingPointTest(double c, double theta, double expected) {
        neutralAxis = new NeutralAxis(c, theta);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }

    @Test
    void setSectionTest() {
        neutralAxis = new NeutralAxis(30, 45);
        Rectangle section = new Rectangle(300, 500);
        assertThatCode(() -> neutralAxis.setSection(section))
                .doesNotThrowAnyException();
    }

}
