package odo.structure.column.pmcurve;

import odo.structure.column.reinforcement.Rebar;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NeutralAxisTest {

    private NeutralAxis neutralAxis;

    @ParameterizedTest
    @CsvSource({"30, 45, -150, 150", "30, 135, -150, -150", "30, 225, 150, -150", "30, 315, 150, 150"})
    void furthestRebarTest(double c, double theta, double expectedX, double expectedY) {
        Rectangle section = new Rectangle(400, 400);
        neutralAxis = new NeutralAxis(c, theta, section);
        List<Rebar> rebars = List.of(
                new Rebar(400, 506, 150, 150),
                new Rebar(400, 506, -150, 150),
                new Rebar(400, 506, -150, -150),
                new Rebar(400, 506, 150, -150)
        );

        Rebar furthestRebar = neutralAxis.furthestRebar(rebars);
        assertThat(furthestRebar.x).isEqualTo(expectedX);
        assertThat(furthestRebar.y).isEqualTo(expectedY);
    }

    @ParameterizedTest
    @CsvSource({"495, 45", "495, 135", "495, 225", "495, 315"})
    void furthestRebarNullTest(double c, double theta) {
        Rectangle section = new Rectangle(400, 400);
        neutralAxis = new NeutralAxis(c, theta, section);
        List<Rebar> rebars = List.of(
                new Rebar(400, 506, 150, 150),
                new Rebar(400, 506, -150, 150),
                new Rebar(400, 506, -150, -150),
                new Rebar(400, 506, 150, -150)
        );

        Rebar furthestRebar = neutralAxis.furthestRebar(rebars);
        assertThat(furthestRebar).isNull();
    }

    @ParameterizedTest
    @CsvSource({"30, 45, 60", "50, 60, 115.47005"})
    void firstQuadrantDistanceTest(double c, double theta, double expected) {
        Rectangle section = new Rectangle(400, 400);
        neutralAxis = new NeutralAxis(c, theta, section);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"150, 135, 300", "180, 120, 415.69219", "120, 120, 277.12812"})
    void secondQuadrantDistanceTest(double c, double theta, double expected) {
        Rectangle section = new Rectangle(300, 500);
        neutralAxis = new NeutralAxis(c, theta, section);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"80, 240, 184.75208", "200, 210, 346.41016", "250, 240, 577.35026", "258, 240, 577.35026"})
    void thirdQuadrantDistanceTest(double c, double theta, double expected) {
        Rectangle section = new Rectangle(300, 500);
        neutralAxis = new NeutralAxis(c, theta, section);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"180, 300, 415.69219", "150, 315, 300", "120, 300, 277.12812"})
    void fourthQuadrantDistanceTest(double c, double theta, double expected) {
        Rectangle section = new Rectangle(300, 500);
        neutralAxis = new NeutralAxis(c, theta, section);
        assertThat(neutralAxis.distanceOfTwoCrossingPoint()).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"30, 50, 266.98484", "-130, 200, 486.18795", "148, -248, 27.17157"})
    void distanceToPointTest(double x, double y, double expected) {
        Rectangle section = new Rectangle(300, 500);
        neutralAxis = new NeutralAxis(30, 45, section);
        assertThat(neutralAxis.distanceToPoint(x, y)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"-150, -100, true", "200, -200, false"})
    void isNegativeValueTest(double x, double y, boolean expected) {
        Rectangle section = new Rectangle(400, 400);
        neutralAxis = new NeutralAxis(100, 45, section);
        assertThat(neutralAxis.isNegativeValue(x, y)).isEqualTo(expected);
    }

}
