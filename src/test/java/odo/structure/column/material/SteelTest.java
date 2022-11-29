package odo.structure.column.material;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SteelTest {

    @Test
    void constructorTest() {
        Steel steel = new Steel(350);
        assertThat(steel.fy).isEqualTo(350);
    }

}
