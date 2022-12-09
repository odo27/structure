package odo.structure.column.pmcurve;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuadrantTest {

    @ParameterizedTest
    @CsvSource({"45, 1, -1", "120, 1, 1", "200, -1, 1", "320, -1, -1"})
    void getPairTest(double theta, int expectedX, int expectedY) {
        List<Integer> pair = Quadrant.getPair(theta);
        assertThat(pair.get(0)).isEqualTo(expectedX);
        assertThat(pair.get(1)).isEqualTo(expectedY);
    }

    @ParameterizedTest
    @CsvSource({"-30", "0", "90", "180", "270", "360", "380"})
    void validateThetaTest(double theta) {
        assertThatThrownBy(() -> Quadrant.getPair(theta))
                .isInstanceOf(IllegalArgumentException.class);
    }
}