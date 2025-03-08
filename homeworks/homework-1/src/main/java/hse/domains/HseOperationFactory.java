package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Operation;
import hse.interfaces.OperationFactory;

public class HseOperationFactory implements OperationFactory {

    @Override
    public Operation createOperation(int id, OperationType operationType, int bankAccountId, double amount, int date,
                                     String description, int categoryId) {
        return new HseOperation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }
}
