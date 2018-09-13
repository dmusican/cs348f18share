import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class ReverseHelloTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void testReverseHello() {
        ReverseHelloMultithreaded.doReverseHello();
        String expectedOutput = "";
        for (int i=50; i >= 1; i--) {
            expectedOutput += "Hello from thread " + i + System.lineSeparator();
        }
        assertEquals(expectedOutput,systemOutRule.getLog());
    }
}
