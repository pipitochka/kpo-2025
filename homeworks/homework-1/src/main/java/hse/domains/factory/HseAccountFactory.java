package hse.domains.factory;

import hse.domains.object.HseBankAccount;
import hse.interfaces.factory.AccountFactory;
import hse.interfaces.object.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class HseAccountFactory implements AccountFactory {

    @Override
    public BankAccount createAccount(int t, String s) {
        BankAccount newAccount = new HseBankAccount(t, s);
        return newAccount;
    }
}
