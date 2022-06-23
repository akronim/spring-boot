package com.example.mdbspringboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("it")
class MdbSpringBootApplicationTests {

	// object underTest
	Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		// given
		int numberOne = 20;
		int numberTwo = 30;

		// when
		int result = calculator.add(numberOne, numberTwo);

		// then
		int expected = 50;
		assertEquals(expected, result);
	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}
}