package hse.domains.factory;

import hse.domains.object.HseAccount;
import hse.interfaces.factory.AccountFactory;
import hse.interfaces.object.Account;
import org.springframework.stereotype.Component;

@Component
public class HseAccountFactory implements AccountFactory {

    @Override
    public Account createAccount(int t, String s) {
        Account newAccount = new HseAccount(t, s);
        return newAccount;
    }
}
