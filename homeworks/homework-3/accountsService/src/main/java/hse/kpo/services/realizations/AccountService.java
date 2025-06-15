package hse.kpo.services.realizations;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import hse.kpo.domain.interfaces.factories.IAccountFactory;
import hse.kpo.domain.interfaces.factories.IOperationFactory;
import hse.kpo.domain.interfaces.objects.IAccount;
import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.domain.realizations.objects.Operation;
import hse.kpo.dto.AccountDto;
import hse.kpo.dto.OperationDto;
import hse.kpo.dto.responses.AccountResponse;
import hse.kpo.dto.responses.OperationListResponse;
import hse.kpo.dto.responses.OperationResponse;
import hse.kpo.mappers.interfaces.IAccountMapper;
import hse.kpo.mappers.interfaces.IOperationMapper;
import hse.kpo.repositories.IAccountRepository;
import hse.kpo.repositories.IOperationRepository;
import hse.kpo.services.interfaces.IAccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Log4j2
@Getter
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    private final IAccountFactory accountFactory;

    private final IOperationFactory operationFactory;

    private final IOperationRepository operationRepository;

    private final IAccountMapper accountMapper;

    private final IOperationMapper operationMapper;

    @Transactional
    @Override
    public AccountResponse createEmptyAccount(String accountName) {
        if (accountName == null || accountName.isBlank() ) {
            return new AccountResponse(
                    false,
                    null,
                    "empty name"
            );
        }
        IAccount account = accountFactory.createAccount(accountName);
        accountRepository.save((Account) account);
        return new AccountResponse(
                true,
                accountMapper.toDto(account),
                null
        );
    }

    @Transactional
    @Override
    public AccountResponse createAccount(String accountName, double initialBalance) {
        if (accountName == null || accountName.isBlank() ) {
            return new AccountResponse(
                    false,
                    null,
                    "empty name"
            );
        }
        if (initialBalance < 0) {
            return new AccountResponse(
                    false,
                    null,
                    "negative balance"
            );
        }

        IAccount account = accountFactory.createAccountWithBalance(accountName, initialBalance);
        accountRepository.save((Account) account);
        return new AccountResponse(
                true,
                accountMapper.toDto(account),
                null
        );
    }

    @Transactional
    @Override
    public AccountResponse getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return new AccountResponse(
                    true,
                    accountMapper.toDto(account.get()),
                    null
            );
        } else {
            return new AccountResponse(
                    false,
                    null,
                    "no such account"
            );
        }
    }

    @Transactional
    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean deleteAccount(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            accountRepository.delete(account.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public OperationResponse getOperation(Long operationId) {
        Optional<Operation> operation = operationRepository.findById(operationId);
        if (operation.isPresent()) {
            return new OperationResponse(
                    true,
                    operationMapper.toDto(operation.get()),
                    null
            );
        } else {
            return new OperationResponse(
                    false,
                    null,
                    "no such operation"
            );
        }
    }

    @Transactional
    @Override
    public boolean deleteOperation(Long operationId) {
        Optional<Operation> operation = operationRepository.findById(operationId);
        if (operation.isPresent()) {
            operationRepository.delete(operation.get());
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public OperationListResponse getOperationsByAccountId(Long accountId) {
        return new OperationListResponse(
                true,
                operationRepository.findByAccount_Id(accountId)
                        .stream()
                        .map(operationMapper::toDto)
                        .collect(Collectors.toList()),
                null
        );
    }

    @Transactional
    @Override
    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll()
                .stream()
                .map(operationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OperationResponse applyOperation(String externalId, Long accountId, OperationType operationType, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (!optionalAccount.isPresent()) {
            return new OperationResponse(
                    false,
                    null,
                    "no such account"
            );
        }
        Account account = optionalAccount.get();
        Optional<Operation> old_operation = operationRepository.findByExternalId(externalId);
        if (old_operation.isPresent()) {
            return new OperationResponse(
                    true,
                    operationMapper.toDto(old_operation.get()),
                    null
            );
        }
        switch (operationType) {
            case INCOME -> {
                IOperation operation = operationFactory.createOperation(externalId, account, operationType, amount);
                operation.setOperationResult(OperationResult.ACCEPTED);
                operationRepository.save((Operation) operation);
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
                account.addOperation((Operation) operation);
                return new OperationResponse(
                        true,
                        operationMapper.toDto(operation),
                        null
                );
            }
            case EXPENSE -> {
                IOperation operation = operationFactory.createOperation(externalId, account, operationType, amount);
                if (account.getBalance() < amount) {
                    operation.setOperationResult(OperationResult.REJECTED);
                } else {
                    account.setBalance(account.getBalance() - amount);
                    operation.setOperationResult(OperationResult.ACCEPTED);
                }
                operationRepository.save((Operation) operation);
                account.addOperation((Operation) operation);
                accountRepository.save(account);
                return new OperationResponse(
                        true,
                        operationMapper.toDto(operation),
                        null
                );
            }
        }

        return new OperationResponse(false, null, "unsupported operation type");
    }
}
