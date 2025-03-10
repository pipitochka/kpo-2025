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

    private int accountCounter = 0;
    private int operationCounter = 0;
    private int categoryCounter = 0;

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
        accountList.add(accountFactory.createAccount(accountCounter++, name));
    }

    @Override
    public void addOperation(OperationType operationType, Account bankAccountId, double amount, int date,
                             String description, Category category) {
        operationList.add(operationFactory.createOperation(operationCounter++, operationType, bankAccountId,
                amount, date, description, category));
    }

    @Override
    public void addCategory(OperationType type, String name) {
        categoryList.add(categoryFactory.createCategory(categoryCounter++, type, name));
    }

    @Override
    public void takeCommand(CommandContext context) {
        if (context == null) {
            return;
        }
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
    public void printAnaliticByAccountByDate(Account account, int dateFrom, int dateTo) {
        if (account == null) {
            return;
        }
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        AtomicReference<Double> totalExpense = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getAccount() == account) {
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
    public void printAnaliticByAccountIncome(Account account, int dateFrom, int dateTo) {
        if (account == null) {
            return;
        }
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getAccount() == account) {
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
    public void printAnaliticByAccountExpense(Account accountId, int dateFrom, int dateTo) {
        if (accountId == null) {
            return;
        }
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getAccount() == accountId) {
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
    public void printAnaliticByAccountByCategory(Account accountId, Category category, int dateFrom, int dateTo) {
        if (accountId == null) {
            return;
        }
        if (category == null) {
            return;
        }
        AtomicReference<Double> totalIncome = new AtomicReference<>((double) 0);
        operationList.forEach(operation -> {
            if (operation.getAccount() == accountId) {
                if (operation.getDate() >= dateFrom && operation.getDate() <= dateTo) {
                    if (operation.getCategory() == category) {
                        totalIncome.updateAndGet(v -> Double.valueOf(v + operation.getAmount()));
                        System.out.println(operation);
                    }
                }
            }
        });
        System.out.println("Total cost: " + totalIncome.get());
    }

    @Override
    public void repeatOperations(Account accountId) {
        if (accountId == null) {
            return;
        }
        accountId.setBalance(0);
        operationList.stream().forEach(operation -> {
            if (operation.getAccount() == accountId) {
                operationHandler.handle(new CommandFromOpperation(operation), this);
            }
        });
    }

    @Override
    public void deleteAccount(Account accountId) {
        if (accountId != null) {
            Iterator<Operation> iterator = operationList.iterator();
            while (iterator.hasNext()) {
                Operation operation = iterator.next();
                if (operation.getAccount() == accountId) {
                    iterator.remove(); // Удаление безопасно
                }
            }
            accountList.remove(accountId);
            System.out.println("Account deleted: " + accountId);
        }
        else{
            System.out.println("Account is null");
        }
    }

    @Override
    public void deleteCategory(Category category) {
        if (category == categoryList.get(0) || category == categoryList.get(1)){
            System.out.println("Category " + category + " cannot be deleted.");
            return;
        }
        if (category != null) {

            operationList.stream().forEach(operation -> {
                    if (operation.getOperationType() == OperationType.INCOME) {
                        operation.setCategory(categoryList.get(0));
                    }
                    if (operation.getOperationType() == OperationType.EXPENSE) {
                        operation.setCategory(categoryList.get(1));
                    }
            });
            categoryList.remove(category);
            System.out.println("Category " + category + " deleted.");
            return;
        }
        else {
            System.out.println("Category " + category + " not found.");
        }
    }

    @Override
    public void reverseOperation(Operation operationId) {
        if (operationId != null) {
            Command command = new CommandFromOpperation(operationId);
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
    public void changeOperationType(Operation operationId, Category newCategory) {
        if (operationId != null){
            if (newCategory != null){
                if (newCategory.getOperationType() == operationId.getOperationType()) {
                    operationId.setCategory(newCategory);
                    System.out.println("Category " + newCategory + " changed.");
                }
                else{
                    System.out.println("Category " + newCategory + "another type");
                }
            }
            else {
                System.out.println("Category " + newCategory + " not found.");
            }
        }
        else {
            System.out.println("Operation " + operationId + " not found.");
        }
    }

    @Override
    public Category getCategory(String name) {
        return categoryList.stream().filter(category -> category.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryList.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

    }

    @Override
    public Account getAccount(String name) {
        return accountList.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Account getAccount(int id) {
        return accountList.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Operation getOperation(int id) {
        return operationList.stream().filter(operation -> operation.getId() == id).findFirst().orElse(null);
    }
}
