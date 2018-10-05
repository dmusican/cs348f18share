package notify;

class Producer extends Thread {
  public void run() {
    while (true) {
      Demo.drop.put(getId() + " "+ Math.random());
    }
  }
}

class Consumer extends Thread {
  public void run() {
    while (true) {
      System.out.println("   Taking: " + Demo.drop.take());
    }
  }
}


class Drop {
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


public class Demo2 {
  public static Drop2 drop = new Drop2();
  public static void main(String[] args) {
    (new Producer2()).start();
    (new Producer2()).start();
    (new Consumer2()).start();
  }}
