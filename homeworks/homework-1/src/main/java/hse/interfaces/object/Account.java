package hse.interfaces.object;

public interface Account {
    public String getName();
    public int getId();
    public double getBalance();
    public void setBalance(double balance);
    public void deposit(double amount);

    public void withdraw(double amount);
}
