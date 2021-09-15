import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

    Counter counter = new Counter();

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
    public void test_add_sub_multiplication_division() {
        // kommenterad kod "smäller"

        assertEquals(5, counter.addSubMultDiv("addition", 2, 3));
        //   assertEquals(9, counter.addSubMultDiv("addition", 2,3));

        assertEquals(-1, counter.addSubMultDiv("subtraction", 2, 3));
        //   assertEquals(1, counter.addSubMultDiv("subtraction", 2,3));

        assertEquals(6, counter.addSubMultDiv("multiplication", 2, 3));
        //   assertEquals(60, counter.addSubMultDiv("multiplication", 2,3));

        //   assertEquals(300, counter.addSubMultDiv("division", 15,5));
        assertEquals(3, counter.addSubMultDiv("division", 15, 5));
        assertEquals(3, counter.addSubMultDiv("division", 15, 0));

        //  assertEquals(3, counter.addSubMultDiv("", 9, 3));
    }

    @Test
    public void test_strings_to_addition() {
        // grön
        assertEquals(4, counter.stringAddSub("2 + 2"));

        assertEquals(6, counter.stringAddSub("2 + 2 * 2"));

        //smäller
        //    assertEquals(4, counter.stringAddSub(""));
    }

    @Test
    public void test_strings_to_subtraction() {
        // denna smäller
        // assertEquals(4,counter.stringAddSub("5 sub 2"));

        // denna är grön - trimmar innan första siffran
        assertEquals(5, counter.stringAddSub("    7 - 2"));

    }

    @Test
    public void test_strings_add_add_success() {
        assertEquals(6, counter.stringAddSub("2 + 2 + 2"));
    }

    @Test
    public void test_strings_add_sub_success() {
        assertEquals(2, counter.stringAddSub("2 + 2 - 2"));
    }

    @Test
    public void test_strings_add_multi_success() {
        assertEquals(6, counter.stringAddSub("2 + 2 * 2"));
    }

    @Test
    public void test_strings_add_division_success() {
        assertEquals(3, counter.stringAddSub("2 + 2 / 2"));
    }

    @Test
    public void test_strings_sub_add_success() {
        assertEquals(2, counter.stringAddSub("2 - 2 + 2"));
    }

    @Test
    public void test_strings_sub_sub_success() {
        assertEquals(-2, counter.stringAddSub("2 - 2 - 2"));
    }

    @Test
    public void test_strings_sub_multi_success() {
        assertEquals(2, counter.stringAddSub("2 - 2 * 2"));
    }

    @Test
    public void test_strings_sub_div_success() {
        assertEquals(2, counter.stringAddSub("2 - 2 / 2"));
    }

    @Test
    public void test_strings_multi_add_success() {
        assertEquals(2, counter.stringAddSub("2 * 2 + 2"));
    }

    @Test
    public void test_strings_multi_sub_success() {
        assertEquals(10, counter.stringAddSub("2 * 6 - 2"));
    }

    @Test
    public void test_strings_multi_multi_success() {
        assertEquals(8, counter.stringAddSub("2 * 2 * 2"));
    }

    @Test
    public void test_strings_multi_div_success() {
        assertEquals(2, counter.stringAddSub("2 * 2 / 2"));
    }

    @Test
    public void test_strings_div_add_success() {
        assertEquals(5, counter.stringAddSub("6 / 2 + 2"));
    }

    @Test
    public void test_strings_div_sub_success() {
        assertEquals(4, counter.stringAddSub("12 / 2 - 2"));
    }

    @Test
    public void test_strings_div_multi_success() {
        assertEquals(30, counter.stringAddSub("20 / 2 * 3"));
    }

    @Test
    public void test_strings_div_div_success() {
        assertEquals(4, counter.stringAddSub("40 / 3 / 3"));
    }

    @Test
    public void test_strings_div_div_fail() {
        assertEquals(6, counter.stringAddSub("40 / 3 / 0"));
    }

    @Test
    public void test_strings_div_multi_fail() {
        assertEquals(6, counter.stringAddSub("40 / 3 * 0"));
    }

    @Test
    public void test_strings_div_multi_zero_fail() {
        assertEquals(6, counter.stringAddSub("40 / 0 * 3"));
    }

    @Test
    public void test_strings_multi_multi_zero_fail() {
        assertEquals(6, counter.stringAddSub("40 * 0 * 3"));
    }

    @Test
    public void test_strings_empty_string_fail() {
        assertEquals(6, counter.stringAddSub(""));
    }
}
