package hse.interfaces.object;

import hse.emums.OperationType;
import java.util.List;

/**
 * interface of facade.
 */
public interface Facade {
    public void addBankAccount(String name);

    public void addOperation(OperationType operationType, Account bankAccountId, double amount, int date,
                             String description, Category category);

    public void addCategory(OperationType type, String name);

    public void takeCommand(CommandContext context);

    public List<Account> getAccountList();

    public List<Operation> getOperationList();

    public List<Category> getCategoryList();

    public void printAnaliticByAccountByDate(Account account, int dateFrom, int dateTo);

    public void printAnaliticByAccountIncome(Account accountId, int dateFrom, int dateTo);

    public void printAnaliticByAccountExpense(Account accountId, int dateFrom, int dateTo);

    public void printAnaliticByAccountByCategory(Account accountId, Category categoryId, int dateFrom, int dateTo);

    public void repeatOperations(Account accountId);

    public void deleteAccount(Account accountId);

    public void deleteCategory(Category categoryId);

    public void reverseOperation(Operation operationId);

    public void changeOperationType(Operation operationId, Category newCategory);

    public Category getCategory(String name);

    public Category getCategoryById(int id);

    public Account getAccount(String name);

    public Account getAccount(int id);

    public Operation getOperation(int id);
}
