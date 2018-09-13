import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SumThreadTest {

    private double sequentialTotal(int[] arr) {
        double sum = 0;
        for (int i=0; i < arr.length; i++) {
            sum += Math.sin((arr[i]));
        }
        return sum;
    }

    @Test
    public void testSum() throws Exception {
        Random r = new Random(90125);
        int[] arr = new int[10_000_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt();
        }
        // Do the sequential test a number of times to warm it up, measure on last
        int numWarmups = 3;
        long timeBefore = 0;
        long seqTime = 0;
        double seqSum = 0;
        for (int i=0; i < numWarmups; i++) {
            timeBefore = System.currentTimeMillis();
            seqSum = sequentialTotal(arr);
            seqTime = System.currentTimeMillis() - timeBefore;
        }
        System.out.println("Sequential sum = " + seqSum);

        double parallelSum = 0;
        long parallelTime = 0;
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads: " + numThreads);

        // Do the parallel test a number of times to warm it up, measure on last
        for (int i=0; i < numWarmups; i++) {
            timeBefore = System.currentTimeMillis();
            parallelSum = SumMultithreaded.sum(arr, numThreads);
            parallelTime = System.currentTimeMillis() - timeBefore;
        }

        System.out.println("Sequential sum = " + seqSum);
        System.out.println("Parallel sum: " + parallelSum);

        System.out.println("Sequential time = " + seqTime);
        System.out.println("Parallel time = " + parallelTime);
        double speedup = ((double)seqTime)/parallelTime;
        System.out.println("Speedup = " + speedup);
        assertTrue(parallelTime <= seqTime / ((2./3)*numThreads));
        assertEquals(parallelSum, seqSum, 1);
    }
}