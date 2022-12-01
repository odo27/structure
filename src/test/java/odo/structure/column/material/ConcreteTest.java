package odo.structure.column.material;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ConcreteTest {

    @Test
    void fckTest() {
        Concrete concrete = new Concrete(24);
        assertThat(concrete.fck).isEqualTo(24);
    }

    @ParameterizedTest
    @CsvSource({"24,0.85", "30,0.836", "50,0.696"})
    void betaOneTest(double fck, double expected) {
        Concrete concrete = new Concrete(fck);
        assertThat(concrete.b1).isEqualTo(expected);
    }

}
