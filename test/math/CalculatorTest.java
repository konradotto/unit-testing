package math;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @BeforeEach


    @Test
    void testAdditionWithPositives() {
        int expected = 5;
        int actual = calculator.add(2,3);
        assertEquals(expected, actual, "2+3 should add up to 5");
    }

    @Test
    void testAdditionWithNegatives() {
        assertTrue(calculator.add(-1,-2) < 0);
    }

}
