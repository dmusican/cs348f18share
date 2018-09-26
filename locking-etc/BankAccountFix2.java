public class BankAccount {
    private int balance;
    private Object bankLock = new Object();

    public void setBalance(int balance) {
        synchronized (bankLock) {
            this.balance = balance;
        }
    }

    public void withdraw(int amount) {
        synchronized (bankLock) {
            int b = balance;
            int a = amount;
            int ans = b - a;
            balance = ans;
        }
    }
}
