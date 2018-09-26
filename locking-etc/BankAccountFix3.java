public class BankAccount {
    private int balance;

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
            int b = balance;
            int a = amount;
            int ans = b - a;
            balance = ans;
    }
}
