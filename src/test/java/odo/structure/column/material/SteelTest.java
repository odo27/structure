package odo.structure.column.material;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SteelTest {

    @Test
    void constructorTest() {
        Steel steel = new Steel(350);
        assertThat(steel.fy).isEqualTo(350);
    }

    @ParameterizedTest
    @CsvSource({"351,350", "270,270", "-200,-200", "-351,-350"})
    void restrictStressTest(double stress, double expected) {
        Steel steel = new Steel(350);
        assertThat(steel.restrictStress(stress)).isEqualTo(expected);
    }

}
