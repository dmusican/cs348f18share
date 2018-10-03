import java.util.concurrent.atomic.*;
class BankAccount3 {
    private static AtomicInteger nextId =
        new AtomicInteger(0);
    private int balance;
    private int id; 

    public synchronized BankAccount3(int balance) {
        this.balance = balance;
        id = nextId.getAndIncrement();
    }
    
    synchronized void withdraw(int amt) { // line A
        balance -= amt;                   // line B
    };                                    // line C

    synchronized void deposit(int amt) {  // line D
        balance += amt;                   // line E
    }                                     // line F









    
    void transferTo(int amt,
                    BankAccount3 other) {
        if (this.id < other.id) {
            synchronized (this) {
                synchronized (other) {
                    this.withdraw(amt);
                    other.deposit(amt);
                }
            }
        } else {
            synchronized (other) {
                synchronized (this) {
                    this.withdraw(amt);
                    other.deposit(amt);
                }
            }
            
        }
    }

    public static void main(String[] args) {
        BankAccount3 one = new BankAccount3(1000);
        BankAccount3 two = new BankAccount3(2000);

        Thread t1 = new Thread() {
            public void run() {
                while (true) {
                    one.transferTo(500,two);
                    System.out.println("Transfering 1");
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                while (true) {
                    two.transferTo(100,one);
                    System.out.println("Transfering 2");
                }
            }
        };

        t1.start();
        t2.start();
    }
}
