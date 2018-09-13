public class SharedCounter {
    static int counter = 0;


    public static void reset() {
        counter = 0;
    }

    public static int increment(int numThreads, int numIncrementsPerThread) throws InterruptedException {
        return counter;
    }
}
