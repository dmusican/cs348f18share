package fork1;

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
    public static final int SEQUENTIAL_CUTOFF = 10;

    public SumThread(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        if (hi - lo < SEQUENTIAL_CUTOFF) {
            for (int i = lo; i < hi; i++) {
                ans += (arr[i]);
            }
        } else {
            SumThread left = new SumThread(arr, lo, (lo+hi)/2);
            SumThread right = new SumThread(arr, (lo+hi)/2, hi);
            left.start();
            right.run();
            try {
                left.join();
                ans = left.ans + right.ans;
            } catch (InterruptedException e) {
                System.out.println("Interrupting cow Moo");
            }
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
    public static double sum(int[] arr) throws InterruptedException {
        SumThread t = new SumThread(arr, 0, arr.length);
        t.run();
        return t.getAns();
    }

    public static void main(String[] args) throws InterruptedException {
        int[] mynums = new int[100];
        for (int i=0; i < mynums.length; i++) {
            mynums[i] = i+1;
        }
        System.out.println(sum(mynums));
    }

}
