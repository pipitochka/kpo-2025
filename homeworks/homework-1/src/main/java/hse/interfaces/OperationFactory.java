package hse.interfaces;

import hse.emums.OperationType;

public interface OperationFactory {
    Operation createOperation(int id, OperationType operationType, int bankAccountId, double amount, int date,
                              String description, int categoryId);
}
