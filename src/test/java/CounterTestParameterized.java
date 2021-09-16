import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class CounterTestParameterized {

    Counter counter = new Counter();
    String value;
    int result;


    public CounterTestParameterized(int result, String value) {
        this.result = result;
        this.value = value;
    }

    @Test
    public void test_strings_div_multi_success() {

        assertEquals(result, counter.stringAddSubParameterized(result, value));
    }

    /*
        vårat nuvarande fungerar enbart så vi måste hårdkoda expected värdet ( expected fungerar som siffra ovan ) men vi måste ha det dynamiskt för parameterized testing
        nedanstående kod fungerar inte ännu, för många argument, först stringvärdet ska vara svaret medans det andra är talet som ska räknas på
     */
    @Parameterized.Parameters
    public static Collection<Object[]> test(){
        return Arrays.asList(new Object[][]{
                {10, "5 + 5"},
                {23, "20 + 2 + 1"},
                {18, "20 - 1 * 2"},
                {11, "20 / 2 + 1"},
                {100, "20 * 5 * 1"},
                {25000, "100 / 2 * 500"},
                {10, "             5 + 10 - 5"},
                {10, "20 / 10 * 5"}
        });
    }
}