package hse.kpo.domain.interfaces.factories;

import hse.kpo.domain.interfaces.objects.IAccount;

public interface IAccountFactory {

    public IAccount createAccount(String accountName);

    public IAccount createAccountWithBalance(String accountName, double balance);
}
