class BankAccount2 {
    private int balance;

    public BankAccount2(int balance) {
        this.balance = balance;
    }
    
    synchronized void withdraw(int amt) { // line A
        balance -= amt;                   // line B
    };                                    // line C

    synchronized void deposit(int amt) {  // line D
        balance += amt;                   // line E
    }                                     // line F

    void transferTo(int amt,
                    BankAccount2 other) {
        synchronized (this) {
            synchronized (other) {
                this.withdraw(amt);      // line G
                other.deposit(amt);      // line H
            }
        }
    }

    public static void main(String[] args) {
        BankAccount2 one = new BankAccount2(1000);
        BankAccount2 two = new BankAccount2(2000);

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
