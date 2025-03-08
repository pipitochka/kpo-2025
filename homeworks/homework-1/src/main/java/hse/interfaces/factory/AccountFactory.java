package hse.interfaces.factory;

import hse.interfaces.object.BankAccount;

public interface AccountFactory {
    public BankAccount createAccount(int number, String name);
}
