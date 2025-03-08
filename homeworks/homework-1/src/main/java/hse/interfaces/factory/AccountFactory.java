package hse.interfaces.factory;

import hse.interfaces.object.Account;

public interface AccountFactory {
    public Account createAccount(int number, String name);
}
