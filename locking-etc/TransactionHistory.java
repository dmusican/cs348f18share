import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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

    // THIS IS BAD.
    // Option 1: don't do this.
    // Option 2. Make a copy of the list and return that.
    // Option 3: see below, but doesn't prevent collisions when I write and they read. Beware.
    public List<Integer> getHistory() {
        return Collections.unmodifiableList(history);
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
