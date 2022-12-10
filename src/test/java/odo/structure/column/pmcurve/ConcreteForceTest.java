package odo.structure.column.pmcurve;

import odo.structure.column.material.Concrete;
import odo.structure.column.section.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ConcreteForceTest {

    private Concrete concrete;

    @BeforeEach
    void setUp() {
        concrete = new Concrete(24);
    }

    @ParameterizedTest
    @CsvSource({"30, 45, 3, 12000", "25, 30, 5, 10392.30485"})
    void calculateTest(double c, double theta, double n, double expected) {
        Rectangle section = new Rectangle(400, 400);
        assertThat(ConcreteForce.calculate(concrete, section, c, theta, n)).isEqualTo(expected, withPrecision(1e-5));
    }

    @ParameterizedTest
    @CsvSource({"300, 300, 212.13203, 45, 1e7, 50, -50", "300, 259.80762, 129.90381, 60, 1e7, 100, -43.30127"})
    void centroidTest(double b, double h, double c, double theta, double n, double expectedX, double expectedY) {
        Rectangle section = new Rectangle(b, h);
        Point centroid = ConcreteForce.centroid(section, c, theta, n);
        assertThat(centroid.x).isEqualTo(expectedX, withPrecision(1e-5));
        assertThat(centroid.y).isEqualTo(expectedY, withPrecision(1e-5));
    }

}
