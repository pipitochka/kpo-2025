package hse.kpo.mappers.realizations;

import hse.kpo.domain.interfaces.objects.IAccount;
import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.dto.AccountDto;
import hse.kpo.mappers.interfaces.IAccountMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements IAccountMapper {

    @Override
    public AccountDto toDto(IAccount account) {
        return new AccountDto(account.getId(), account.getName(), account.getBalance());
    }
}
