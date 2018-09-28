public class BankAccount {
    private int balance;

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        //balance = balance - amount;
        int b = balance;
        int a = amount;
        int ans = b - a;
        balance = ans;
    }
}
