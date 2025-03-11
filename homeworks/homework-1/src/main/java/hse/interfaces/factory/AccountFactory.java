package hse.interfaces.factory;

import hse.interfaces.object.Account;

/**
 * interface of account factory.
 */
public interface AccountFactory {
    public Account createAccount(int number, String name);
}
