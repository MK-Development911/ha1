package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("should display one percent of the number on screen")
    void testPercent(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%");

        String expected = "1.0";
        String actual = calc.readScreen();

        assertEquals(expected, actual, "result should be 1.0!");
        
    }

    @Test
    @DisplayName("should display the result when clicking a second operator after the second digit")
    void testAddingNumbersWithSameValue(){
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");

        String expected = "10";
        String actual = calc.readScreen();

        assertEquals(expected, actual, "result should be 10");

    }

    @Test
    @DisplayName("should clear the screen first, then pressing it the second time should delete all saved values")
    void testClearButton() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("+");
        calc.pressClearKey();

        // Test 1: Erster Druck von C/CE löscht den aktuellen Inhalt des Bildschirms und setzt ihn auf 0.
        assertEquals("0", calc.readScreen(), "Screen should reset to 0 after first clear");
        assertEquals(4.0, calc.displayLatestValue(), "Stored value should still be 4 after first clear");
        assertEquals("+", calc.displayLatestOperation(), "Stored operator should still exist after first clear");

        calc.pressClearKey();

        // Test 2: Zweiter Druck von C/CE setzt die gespeicherten Werte ebenfalls auf 0.
        assertEquals(0.0, calc.displayLatestValue(), "Stored value should be wiped after second clear");
        assertEquals("", calc.displayLatestOperation(), "Stored operator should be wiped after second clear");
    }
}

