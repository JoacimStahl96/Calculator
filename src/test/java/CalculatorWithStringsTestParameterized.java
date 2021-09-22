import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorWithStringsTestParameterized {

    CalculatorWithStrings calculatorWithStrings = new CalculatorWithStrings();
    String value;
    int result;

    public CalculatorWithStringsTestParameterized(int result, String value) {
        this.result = result;
        this.value = value;
    }

    @Test
    public void test_strings_div_multi_success(){
        assertEquals(result, calculatorWithStrings.mathValueFromStrings(value));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> test(){
        return Arrays.asList(new Object[][]{
                {10, "5 + 5"},
                {10, "5 - 5 + 5 + 5"},
                {23, "20 + 2 + 1"},
                {18, "20 - 1 * 2"},
                {30, "20 + 5 * 2"},
                {25, "20 + 10 / 2"},
                {40, "20 / 2 * 4"},
                {11, "20 / 2 + 1"},
                {100, "20 * 5 * 1"},
                {25000, "100 / 2 * 500"},
                {10, "      5 + 10 - 5"},
                {10, "20 / 10 * 5"},
                {15, "20 / 10 * 5 + 5 "}
        });
    }
}