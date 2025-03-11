package hse.domains.factory;

import hse.domains.object.HseOperation;
import hse.emums.OperationType;
import hse.interfaces.factory.OperationFactory;
import hse.interfaces.object.Account;
import hse.interfaces.object.Category;
import hse.interfaces.object.Operation;
import org.springframework.stereotype.Component;

/**
 * Factory to makes operations.
 */
@Component
public class HseOperationFactory implements OperationFactory {

    @Override
    public Operation createOperation(int id, OperationType operationType, Account bankAccountId,
                                     double amount, int date, String description, Category categoryId) {
        return new HseOperation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }
}
