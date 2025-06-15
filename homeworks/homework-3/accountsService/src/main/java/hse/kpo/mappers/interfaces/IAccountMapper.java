package hse.kpo.mappers.interfaces;

import hse.kpo.domain.interfaces.objects.IAccount;
import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.dto.AccountDto;

public interface IAccountMapper {

    public AccountDto toDto(IAccount account);
}
