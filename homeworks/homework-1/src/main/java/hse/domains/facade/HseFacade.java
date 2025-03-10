package hse.domains.facade;

import hse.domains.command.CommandFromOpperation;
import hse.emums.OperationType;
import hse.interfaces.object.CommandContext;
import hse.interfaces.factory.AccountFactory;
import hse.interfaces.factory.CategoryFactory;
import hse.interfaces.factory.CommandBuilder;
import hse.interfaces.factory.OperationFactory;
import hse.interfaces.object.*;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ToString
@Component
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
    private final CommandBuilder commandBuilder;
    @Getter
    private final OperationHandler operationHandler;


    public HseFacade(AccountFactory accountFactory, CategoryFactory categoryFactory, OperationFactory operationFactory, CommandBuilder commandBuilder, OperationHandler operationHandler) {
        this.accountFactory = accountFactory;
        this.categoryFactory = categoryFactory;
        this.operationFactory = operationFactory;
        this.commandBuilder = commandBuilder;
        this.operationHandler = operationHandler;
        addCategory(OperationType.INCOME, "nullIncome");
        addCategory(OperationType.EXPENSE, "nullExpense");
    }

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
        Command command = commandBuilder.createCommand(context);
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

    @Override
    public void deleteAccount(int accountId) {
        if (accountList.get(accountId) != null) {
            accountList.remove(accountId);
            Iterator<Operation> iterator = operationList.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getBankAccountId() == accountId) {
                    iterator.remove();
                }
            }
            System.out.println("Account " + accountId + " deleted.");
        }
        else {
            System.out.println("Account " + accountId + " not found.");
        }

    }

    @Override
    public void deleteCategory(int categoryId) {
        if (categoryId == 0 || categoryId == 1){
            System.out.println("Category " + categoryId + " cannot be deleted.");
            return;
        }
        if (categoryList.get(categoryId) != null) {
            categoryList.remove(categoryId);

            operationList.stream().forEach(operation -> {
                if (operation.getCategoryId() == categoryId) {
                    if (operation.getOperationType() == OperationType.INCOME) {
                        operation.setCategoryId(0);
                    }
                    if (operation.getOperationType() == OperationType.EXPENSE) {
                        operation.setCategoryId(1);
                    }
                }
            });
            System.out.println("Category " + categoryId + " deleted.");
            return;
        }
        else {
            System.out.println("Category " + categoryId + " not found.");
        }
    }

    @Override
    public void reverseOperation(int operationId) {
        if (operationList.get(operationId) != null) {
            Command command = new CommandFromOpperation(operationList.get(operationId));
            switch (command.getContext().getOperationType()){
                case INCOME:
                    command.getContext().setOperationType(OperationType.EXPENSE);
                    break;
                case EXPENSE:
                    command.getContext().setOperationType(OperationType.INCOME);
                    break;
            }
            if (operationHandler.handle(command, this)) {
                operationList.remove(operationId);
            }
        }
        else{
            System.out.println("Operation " + operationId + " not found.");
        }
    }

    @Override
    public void changeOperationType(int operationId, int newCategoryId) {
        if (operationList.get(operationId) != null){
            if (categoryList.get(newCategoryId) != null){
                if (categoryList.get(operationList.get(operationId).getCategoryId()).getOperationType() ==
                    categoryList.get(newCategoryId).getOperationType()) {
                    operationList.get(operationId).setCategoryId(newCategoryId);
                    System.out.println("Category " + newCategoryId + " changed.");
                }
                else{
                    System.out.println("Category " + newCategoryId + "another type");
                }
            }
            else {
                System.out.println("Category " + newCategoryId + " not found.");
            }
        }
        else {
            System.out.println("Operation " + operationId + " not found.");
        }
    }
}
