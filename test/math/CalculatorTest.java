package math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;
    
    @Before
    public void init(){
        calculator = new Calculator();
    }

    @Test
    void testAdditionWithPositives() throws IntegerOverflowException {
        int expected = 5;
        int actual = calculator.add(2,3);
        assertEquals(expected, actual, "2+3 should add up to 5");
    }

    @Test
    void testAdditionWithNegatives() throws IntegerOverflowException {
        int threshold = 0;
        int actual = calculator.add(-1,-2);
        assertTrue(actual < threshold);
    }

    @Test
    void testAdditionWithOverflow() throws IntegerOverflowException {
        assertThrows(IntegerOverflowException.class, () -> {
            calculator.add(Integer.MAX_VALUE, 3);
        }, "Should throw an exception since an overflow will occur.");
    }

    @Test
    void testAdditionWithUnderflow() throws IntegerOverflowException {
        assertThrows(IntegerOverflowException.class, () -> {
            calculator.add(Integer.MIN_VALUE, -1);
        }, "Should throw an exception since an underflow will occur.");
    }

    @Test
    void testAdditionWithoutOverflow() throws IntegerOverflowException {
        int expected = -1;
        int actual = calculator.add(Integer.MAX_VALUE, Integer.MIN_VALUE);
        assertEquals(expected, actual, "Integer.MAX_VaLUE + INTEGER.MIN_VALUE should add up to " + expected + " but is " + actual);
    }

    @Test
    void testDoublePrecision() {
        double term1 = Math.pow(Math.PI, 2);
        double term2 = Math.pow(Math.pow(Math.PI, 6), 1.0/3.0);
        assertEquals(term1, term2, "The two passed terms should be mathematically identical");
    }

    @Test
    void testDoublePrecisionNumeric() {
        double term1 = Math.pow(Math.PI, 2);
        double term2 = Math.pow(Math.pow(Math.PI, 6), 1.0/3.0);
        double delta = 1e-8;
        assertEquals(term1, term2, delta, "The two passed terms should be numerically identical");
    }

}
