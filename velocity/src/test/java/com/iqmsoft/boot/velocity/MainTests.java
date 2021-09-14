package com.iqmsoft.boot.velocity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class MainTests {

	Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
    	// given
		int numberOne = 20;
		int numberTwo = 30;

		// when
		int result = underTest.add(numberOne, numberTwo);

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
