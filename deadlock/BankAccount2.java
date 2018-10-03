class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }
    
    synchronized void withdraw(int amt) { // line A
        balance -= amt;                   // line B
    };                                    // line C

    synchronized void deposit(int amt) {  // line D
        balance += amt;                   // line E
    }                                     // line F

    synchronized void transferTo(int amt,
                                 BankAccount other) {
        this.withdraw(amt);               // line G
        other.deposit(amt);               // line H                    
    }

    public static void main(String[] args) {
        BankAccount one = new BankAccount(1000);
        BankAccount two = new BankAccount(2000);

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
