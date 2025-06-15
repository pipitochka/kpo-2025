package hse.kpo.services.interfaces;

import hse.kpo.domain.enums.OperationType;
import hse.kpo.domain.interfaces.objects.IAccount;
import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.dto.AccountDto;
import hse.kpo.dto.OperationDto;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;

import java.util.List;

public interface IAccountService {

    public AccountResponse createEmptyAccount(String accountName);

    public AccountResponse createAccount(String accountName, double initialBalance);

    public AccountResponse getAccount(Long accountId);

    public List<AccountDto> getAllAccounts();

    public boolean deleteAccount(Long accountId);

    public OperationResponse applyOperation(String externalId, Long accountId,
                                            OperationType operationType, double amount);

    public OperationListResponse getOperationsByAccountId(Long accountId);

    public OperationResponse getOperation(Long operationId);

    public boolean deleteOperation(Long operationId);

    public List<OperationDto> getAllOperations();
}
