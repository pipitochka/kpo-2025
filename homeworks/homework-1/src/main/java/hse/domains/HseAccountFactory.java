package hse.domains;

import hse.interfaces.AccountFactory;
import hse.interfaces.BankAccount;

public class HseAccountFactory implements AccountFactory {
    @Override
    public BankAccount createAccount(int t, String s) {
        BankAccount newAccount = new HseBankAccount(t, s);
        return newAccount;
    }
}
