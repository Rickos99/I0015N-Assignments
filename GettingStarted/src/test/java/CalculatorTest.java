import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @DisplayName("Basic addition")
    @CsvSource(value = {"1,2,3", "-30,33,3", "-0,45,45", "0,0,0"})
    void add(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest
    @DisplayName("Basic subtraction")
    @CsvSource(value = {"1,2,-1", "-30,33,-63", "-0,45,-45", "0,0,0"})
    void subtract(int a, int b, int expected) {
        assertEquals(expected, calculator.subtract(a, b));
    }

    @ParameterizedTest
    @DisplayName("Basic division")
    @CsvSource(value = {"1,2,0.5", "-30,30,-1", "-0,45,0"})
    void divide(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a, b));
    }

    @ParameterizedTest
    @DisplayName("Division with 0")
    @CsvSource(value = {"0,0,NaN", "-1,0,-Infinity", "1,0,Infinity"})
    void divide_with_zero(int a, int b, double expected) {
        assertEquals(expected, calculator.divide(a, b));
    }
    @ParameterizedTest
    @DisplayName("First 100 prime numbers")
    @CsvSource(value = {"2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97"})
    void isPrime(int a){
        assertTrue(calculator.isPrime(a));
    }

    @ParameterizedTest
    @DisplayName("Non prime numbers")
    @CsvSource(value = {"4", "6", "9", "15", "21", "-45", "1", "0"})
    void isNotPrime(int a){
        assertFalse(calculator.isPrime(a));
    }
}
