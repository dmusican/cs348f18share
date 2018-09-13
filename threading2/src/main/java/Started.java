class ExampleThread extends Thread {
    private int id;
    public ExampleThread(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        int total = 0;
        for (int k=0; k < 10000; k++) {
            for (int j = 0; j < 100000000; j++) {
                total += j;
            }
        }
        System.out.println("I, " + id + ", am printing for you... this: " + total);
    }
}

public class Started {
    public static void main(String[] args) {
        for (int i=0; i < 20; i++) {
            ExampleThread t = new ExampleThread(i);
            t.start();
        }
    }
}
