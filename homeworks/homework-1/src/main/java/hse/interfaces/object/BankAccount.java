package hse.interfaces.object;

public interface BankAccount {
    public String getName();
    public int getId();
    public double getBalance();

    public void deposit(double amount);

    public void withdraw(double amount);
}
