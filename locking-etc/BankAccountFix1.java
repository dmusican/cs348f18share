public class BankAccount {
    private int balance;
    private boolean busy = false;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        while (busy) {};

        busy = true;
        int b = balance;
        int a = amount;
        int ans = b - a;
        balance = ans;
        busy = false;
    }
}
