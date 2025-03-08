package hse.interfaces.factory;

import hse.emums.OperationType;
import hse.interfaces.object.Operation;

public interface OperationFactory {
    Operation createOperation(int id, OperationType operationType, int bankAccountId, double amount, int date,
                              String description, int categoryId);
}
