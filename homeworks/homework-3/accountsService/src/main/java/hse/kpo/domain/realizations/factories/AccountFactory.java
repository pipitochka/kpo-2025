package hse.kpo.domain.realizations.factories;

import hse.kpo.domain.interfaces.factories.IAccountFactory;
import hse.kpo.domain.interfaces.objects.IAccount;
import hse.kpo.domain.realizations.objects.Account;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AccountFactory implements IAccountFactory {


    @Override
    public Account createAccount(String accountName) {
        Account account = new Account();
        account.setName(accountName);
        account.setBalance(0);
        return account;
    }

    @Override
    public Account createAccountWithBalance(String accountName, double balance) {
        Account account = new Account();
        account.setName(accountName);
        account.setBalance(balance);
        return account;
    }
}
