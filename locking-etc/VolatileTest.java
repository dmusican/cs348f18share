// https://dzone.com/articles/java-volatile-keyword0-
public class VolatileTest {

    private static int sharedValue = 0;

    public static void main(String[] args) {
        // new Reader().start();
        new Incrementer().start();
    }

    public static class Reader extends Thread {
        @Override
        public void run() {
            int localValue = sharedValue;
            while (true){
                if( localValue != sharedValue){
                    System.out.println(
                        "Read new sharedValue : "+
                        sharedValue);
                    localValue = sharedValue;
                }
            }
        }
    }

    public static class Incrementer extends Thread{
        @Override
        public void run() {
            int localValue = sharedValue;
            while (true){
                localValue++;
                System.out.println(
                    "Incrementing sharedValue to " +
                    localValue);
                sharedValue = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
