package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.*;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HseFacade implements Facade {

    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Operation> operationList = new ArrayList<>();
    private final List<Category> categoryList = new ArrayList<>();

    @Getter
    private AccountFactory accountFactory;
    @Getter
    private CategoryFactory categoryFactory;
    @Getter
    private OperationFactory operationFactory;

    public void addBankAccount(String name) {
        bankAccountList.add(accountFactory.createAccount(bankAccountList.size(), name));
    }

    @Override
    public void addOperation(OperationType operationType, int bankAccountId, double amount, int date,
                             String description, int categoryId) {
        operationList.add(operationFactory.createOperation(operationList.size(), operationType, bankAccountId,
                amount, date, description, categoryId));
    }

    public void addCategory(OperationType type, String name) {
        categoryList.add(categoryFactory.createCategory(categoryList.size(), type, name));
    }
}
