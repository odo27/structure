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

    @ParameterizedTest
    @CsvSource({"30, 50, 266.98484", "-130, 200, 486.18795", "148, -248, 27.17157"})
    void distanceToPointTest(double x, double y, double expected) {
        neutralAxis = new NeutralAxis(30, 45);
        Rectangle section = new Rectangle(300, 500);
        neutralAxis.setSection(section);
        assertThat(neutralAxis.distanceToPoint(x, y)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"-150, -100, true", "200, -200, false"})
    void isNegativeValueTest(double x, double y, boolean expected) {
        neutralAxis = new NeutralAxis(100, 45);
        Rectangle section = new Rectangle(400, 400);
        neutralAxis.setSection(section);
        assertThat(neutralAxis.isNegativeValue(x, y)).isEqualTo(expected);
    }

    @Test
    void setSectionTest() {
        neutralAxis = new NeutralAxis(30, 45);
        Rectangle section = new Rectangle(300, 500);
        assertThatCode(() -> neutralAxis.setSection(section))
                .doesNotThrowAnyException();
    }

}
