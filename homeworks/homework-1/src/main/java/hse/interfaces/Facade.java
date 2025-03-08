package hse.interfaces;

import hse.emums.OperationType;

public interface Facade {
    public void addBankAccount(String name);

    public void addOperation(OperationType operationType, int bankAccountId, double amount, int date,
                             String description, int categoryId);

    public void addCategory(OperationType type, String name);
}
