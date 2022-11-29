package odo.structure.column.material;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConcreteTest {

    @Test
    void constructorTest() {
        Concrete concrete = new Concrete(24);
        assertThat(concrete.fck).isEqualTo(24);
    }

}
