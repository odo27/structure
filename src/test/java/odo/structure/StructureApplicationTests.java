package odo.structure;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class StructureApplicationTests {

	@Test
	void mainTest() {
		assertThatCode(() -> StructureApplication.main(null))
				.doesNotThrowAnyException();
	}

}
