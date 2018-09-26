import java.util.ArrayList;

// What does it take to fix this class to be threadsafe for all possible uses,
// not necessarily just the sample main that I provided?
public class TransactionHistory {

    private ArrayList<Integer> history;

    public TransactionHistory() {
        history = new ArrayList<Integer>();
    }

    public synchronized void add(int value) {
        history.add(value);
    }

    public synchronized void remove(int value) {
        history.remove(value);
    }

    public ArrayList<Integer> getHistory() {
        return history;
    }

    public static void main(String[] args) throws InterruptedException {
        // some sample usage only
        TransactionHistory history = new TransactionHistory();
        Thread t1 = new Thread( () -> {
                for (int i=0; i < 100; i++) {
                    history.add(i);
                }
        });

        Thread t2 = new Thread( () -> {
                for (int i=500; i < 600; i++) {
                    history.add(i);
                }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(history.getHistory().size());
    }

}
