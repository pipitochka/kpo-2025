package hse.domains.facade;

import hse.emums.OperationType;
import hse.interfaces.CommandContext;
import hse.interfaces.factory.AccountFactory;
import hse.interfaces.factory.CategoryFactory;
import hse.interfaces.factory.CommandFactory;
import hse.interfaces.factory.OperationFactory;
import hse.interfaces.object.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ToString
@Component
@RequiredArgsConstructor
public class HseFacade implements Facade {

    private final List<BankAccount> bankAccountList = new ArrayList<>();
    private final List<Operation> operationList = new ArrayList<>();
    private final List<Category> categoryList = new ArrayList<>();

    @Getter
    private final AccountFactory accountFactory;
    @Getter
    private final CategoryFactory categoryFactory;
    @Getter
    private final OperationFactory operationFactory;
    @Getter
    private final CommandFactory commandFactory;

    @Override
    public void addBankAccount(String name) {
        bankAccountList.add(accountFactory.createAccount(bankAccountList.size(), name));
    }

    @Override
    public void addOperation(OperationType operationType, int bankAccountId, double amount, int date,
                             String description, int categoryId) {
        operationList.add(operationFactory.createOperation(operationList.size(), operationType, bankAccountId,
                amount, date, description, categoryId));
    }

    @Override
    public void addCategory(OperationType type, String name) {
        categoryList.add(categoryFactory.createCategory(categoryList.size(), type, name));
    }

    @Override
    public void takeCommand(CommandContext context) {
        Command command = commandFactory.createCommand(context);
        command.execute(this);
    }
}
