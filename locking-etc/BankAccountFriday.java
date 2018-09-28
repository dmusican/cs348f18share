public class BankAccount {
    private int balance;

    public synchronized void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized void getBalance() {
        return balance;
    }
    
    public void withdraw(int amount) {
        setBalance(getBalance()-amount);
    }
}
