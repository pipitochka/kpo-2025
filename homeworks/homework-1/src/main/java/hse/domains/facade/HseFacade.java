package hse.domains.facade;

import hse.domains.command.CommandFromOpperation;
import hse.emums.OperationType;
import hse.interfaces.object.CommandContext;
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
import java.util.concurrent.atomic.AtomicReference;

@ToString
@Component
@RequiredArgsConstructor
public class HseFacade implements Facade {

    @Getter
    private List<Account> accountList = new ArrayList<>();
    @Getter
    private List<Operation> operationList = new ArrayList<>();
    @Getter
    private List<Category> categoryList = new ArrayList<>();

    @Getter
    private final AccountFactory accountFactory;
    @Getter
    private final CategoryFactory categoryFactory;
    @Getter
    private final OperationFactory operationFactory;
    @Getter
    private final CommandFactory commandFactory;
    @Getter
    private final OperationHandler operationHandler;


    @Override
    public void addBankAccount(String name) {
        accountList.add(accountFactory.createAccount(accountList.size(), name));
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
        if (operationHandler != null) {
            if (operationHandler.handle(command, this)){
                command.execute(this);
                System.out.println("Command taken");
            }
            else{
                System.out.println("Invalid command");
            }
        }
    }

    @Override
    public void printAnaliticByAccountByDate(int accountId, int dateFrom, int dateTo) {
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        AtomicReference<Double> totalExpense = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getBankAccountId() == accountId) {
                if (operation.getDate() >= dateFrom && operation.getDate() <= dateTo) {
                    switch (operation.getOperationType()) {
                        case INCOME:
                            totalIncome.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                            System.out.println(operation);
                            break;
                        case EXPENSE:
                            totalExpense.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                            System.out.println(operation);
                            break;
                    }
                }
            }
        });
        System.out.println("Total income: " + totalIncome.get());
        System.out.println("Total expense: " + totalExpense.get());
    }

    @Override
    public void printAnaliticByAccountIncome(int accountId, int dateFrom, int dateTo) {
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getBankAccountId() == accountId) {
                if (operation.getDate() >= dateFrom && operation.getDate() <= dateTo) {
                    if (operation.getOperationType() == OperationType.INCOME) {
                        totalIncome.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                        System.out.println(operation);
                    }
                }
            }
        });
        System.out.println("Total income: " + totalIncome.get());
    }

    @Override
    public void printAnaliticByAccountExpense(int accountId, int dateFrom, int dateTo) {
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getBankAccountId() == accountId) {
                if (operation.getDate() >= dateFrom && operation.getDate() <= dateTo) {
                    if (operation.getOperationType() == OperationType.EXPENSE) {
                        totalIncome.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                        System.out.println(operation);
                    }
                }
            }
        });
        System.out.println("Total expense: " + totalIncome.get());
    }

    @Override
    public void printAnaliticByAccountByCategory(int accountId, int categoryId, int dateFrom, int dateTo) {
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getBankAccountId() == accountId) {
                if (operation.getDate() >= dateFrom && operation.getDate() <= dateTo) {
                    if (operation.getCategoryId() == categoryId) {
                        totalIncome.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                        System.out.println(operation);
                    }
                }
            }
        });
        System.out.println("Total cost: " + totalIncome.get());
    }

    @Override
    public void repeatOperations(int accountId) {
        accountList.get(accountId).setBalance(0);
        operationList.stream().forEach(operation -> {
            if (operation.getBankAccountId() == accountId) {
                operationHandler.handle(new CommandFromOpperation(operation), this);
            }
        });
    }


}
