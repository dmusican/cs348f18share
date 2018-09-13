/**
 * This thread finds the sum of the sine values of a subsection of an array.
 * I know, summing the sine values (i.e., Math.sin) seems like a silly task.
 * I'm doing it because calculating the sine value is nice and slow. That helps
 * make the parallelism pay off.
 */
class SumThread extends Thread {
    private int lo, hi;
    private int[] arr;
    private double ans = 0;

    public SumThread(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = lo; i < hi; i++) {
            ans += Math.sin(arr[i]);
        }
    }

    public double getAns() {
        return ans;
    }
}

public class SumMultithreaded {
    /**
     * Sum the sine of the elements of an array.
     *
     * @param arr array to sum
     * @return sum of the array's elements
     * @throws InterruptedException shouldn't happen
     */
    public static double sum(int[] arr, int numThreads) throws InterruptedException {
        int len = arr.length;
        double ans = 0;

        // Create and start threads.
        SumThread[] ts = new SumThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            ts[i] = new SumThread(arr, (i * len) / numThreads, ((i + 1) * len / numThreads));
            ts[i].start();
        }

        // Wait for the threads to finish and sum their results.
        for (int i = 0; i < numThreads; i++) {
            ts[i].join();
            ans += ts[i].getAns();
        }
        return ans;
    }

}
