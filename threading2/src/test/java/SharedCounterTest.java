import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SharedCounterTest {

    @Test
    public void testCounter() throws Exception {
        for (int i = 0; i < 10; i++) {
            SharedCounter.reset();
            int result = SharedCounter.increment(1, 10);
            assertEquals(10, result);
        }

        int total = 0;
        for (int i = 0; i < 10; i++) {
            SharedCounter.reset();
            int result = SharedCounter.increment(20, 10);
            total += result;
        }
        System.out.println("Total (should be 2000 if no races) = " + total);
        assertNotEquals(2000, total);
    }
}
