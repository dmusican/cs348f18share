public class BankAccount {
    private int balance;

    public void setBalance(int balance) {
        synchronized (this) {
            this.balance = balance;
        }
    }

    public void withdraw(int amount) {
        synchronized (this) {
            int b = balance;
            int a = amount;
            int ans = b - a;
            balance = ans;
        }
    }
}
