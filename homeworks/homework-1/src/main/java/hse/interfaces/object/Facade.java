package hse.interfaces.object;

import hse.emums.OperationType;
import hse.interfaces.CommandContext;

public interface Facade {
    public void addBankAccount(String name);

    public void addOperation(OperationType operationType, int bankAccountId, double amount, int date,
                             String description, int categoryId);

    public void addCategory(OperationType type, String name);

    public void takeCommand(CommandContext context);
}
