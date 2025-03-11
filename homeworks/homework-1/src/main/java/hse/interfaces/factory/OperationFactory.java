package hse.interfaces.factory;

import hse.emums.OperationType;
import hse.interfaces.object.Account;
import hse.interfaces.object.Category;
import hse.interfaces.object.Operation;

/**
 * interface of operation factory.
 */
public interface OperationFactory {
    Operation createOperation(int id, OperationType operationType, Account bankAccountId, double amount, int date,
                              String description, Category category);
}
