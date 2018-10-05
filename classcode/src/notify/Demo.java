package notify;

class Producer2 extends Thread {
  public void run() {
    while (true) {
      Demo2.drop.put(getId() + " "+ Math.random());
    }
  }
}

class Consumer2 extends Thread {
  public void run() {
    while (true) {
      System.out.println("   Taking: " + Demo2.drop.take());
    }
  }
}


class Drop2 {
  private String message;
  private boolean full = false;

  public synchronized String take() {
    try {
      if (!full) wait();
      full = false;
      notify();
      return message;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void put(String message) {
    try {
      if (full) wait();
      full = true;
      System.out.println("Putting: " + message);
      this.message = message;
      notify();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}


public class Demo {
  public static Drop drop = new Drop();
  public static void main(String[] args) {
    (new Producer()).start();
    (new Producer()).start();
    (new Consumer()).start();
  }}
