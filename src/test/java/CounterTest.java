import org.junit.Test;
import org.w3c.dom.css.Counter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CounterTest {

    CalculatorWithStrings counter = new CalculatorWithStrings();

    @Test
    public void test_addition_success() {
        assertEquals(2, counter.addition(1, 1));
        assertEquals(3, counter.addition(2, 1));
        assertEquals(6, counter.addition(3, 3));
        assertEquals(5, counter.addition(4, 1));
        assertEquals(9, counter.addition(5, 4));
    }

    @Test
    public void test_subtraction_success() {
        assertEquals(2, counter.subtraction(3, 1));
        assertEquals(4, counter.subtraction(6, 2));
        assertEquals(9, counter.subtraction(10, 1));
        assertEquals(12, counter.subtraction(22, 10));
    }

    @Test
    public void test_strings_to_addition() {
        // grön
        assertEquals(4, counter.mathValueFromStrings("2 + 2"));

        assertEquals(6, counter.mathValueFromStrings("2 + 2 * 2"));

        //smäller
        //    assertEquals(4, counter.stringAddSub(""));
    }

    @Test
    public void test_strings_to_subtraction() {
        // denna smäller
        // assertEquals(4,counter.stringAddSub("5 sub 2"));

        // denna är grön - trimmar innan första siffran
        assertEquals(5, counter.mathValueFromStrings("    7 - 2"));

    }

    @Test
    public void test_strings_add_add_success() {
        assertEquals(6, counter.mathValueFromStrings("2 + 2 + 2"));
    }

    @Test
    public void test_strings_add_sub_success() {
        assertEquals(2, counter.mathValueFromStrings("2 + 2 - 2"));
    }

    @Test
    public void test_strings_add_multi_success() {
        assertEquals(6, counter.mathValueFromStrings("2 + 2 * 2"));
    }

    @Test
    public void test_strings_add_division_success() {
        assertEquals(3, counter.mathValueFromStrings("2 + 2 / 2"));
    }

    @Test
    public void test_strings_sub_add_success() {
        assertEquals(2, counter.mathValueFromStrings("2 - 2 + 2"));
    }

    @Test
    public void test_strings_sub_sub_success() {
        assertEquals(-2, counter.mathValueFromStrings("2 - 2 - 2"));
    }

    @Test
    public void test_strings_sub_multi_success() {
        assertEquals(-2, counter.mathValueFromStrings("2 - 2 * 2"));
    }

    @Test
    public void test_strings_sub_div_success() {
        assertEquals(1, counter.mathValueFromStrings("2 - 2 / 2"));
    }

    @Test
    public void test_strings_multi_add_success() {
        assertEquals(6, counter.mathValueFromStrings("2 * 2 + 2"));
    }

    @Test
    public void test_strings_multi_sub_success() {
        assertEquals(10, counter.mathValueFromStrings("2 * 6 - 2"));
    }

    @Test
    public void test_strings_multi_multi_success() {
        assertEquals(8, counter.mathValueFromStrings("2 * 2 * 2"));
    }

    @Test
    public void test_strings_multi_div_success() {
        assertEquals(2, counter.mathValueFromStrings("2 * 2 / 2"));
    }

    @Test
    public void test_strings_div_add_success() {
        assertEquals(5, counter.mathValueFromStrings("6 / 2 + 2"));
    }

    @Test
    public void test_strings_div_sub_success() {
        assertEquals(4, counter.mathValueFromStrings("12 / 2 - 2"));
    }

    @Test
    public void test_strings_div_multi_success() {
        assertEquals(30, counter.mathValueFromStrings("20 / 2 * 3"));
    }

    @Test
    public void test_strings_div_div_success() {
        assertEquals(4, counter.mathValueFromStrings("40 / 3 / 3"));
    }

    @Test
    public void test_strings_div_div_fail() {
        ArithmeticException illegalArgumentException = assertThrows(ArithmeticException.class, () -> counter.mathValueFromStrings("40 / 3 / 0"));
        assertEquals("Division by zero", illegalArgumentException.getMessage());
    }

    @Test
    public void test_strings_div_multi_zero_fail() {
        ArithmeticException illegalArgumentException = assertThrows(ArithmeticException.class, () -> counter.mathValueFromStrings("40 / 0 * 3"));
        assertEquals("Division by zero", illegalArgumentException.getMessage());
    }

    @Test
    public void test_strings_empty_string_fail() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> counter.mathValueFromStrings(""));
        assertEquals("No value found", illegalArgumentException.getMessage());
    }

    @Test
    public void test_strings_entered_letter_fail() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> counter.mathValueFromStrings("a"));
        assertEquals("Invalid character", illegalArgumentException.getMessage());
    }

    @Test
    public void test_strings_entered_letter_in_middle_fail() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> counter.mathValueFromStrings("3 + 2a * 2"));
        assertEquals("Invalid character", illegalArgumentException.getMessage());
    }
}
