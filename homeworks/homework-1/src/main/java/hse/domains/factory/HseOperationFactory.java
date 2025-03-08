package hse.domains.factory;

import hse.domains.object.HseOperation;
import hse.emums.OperationType;
import hse.interfaces.object.Operation;
import hse.interfaces.factory.OperationFactory;
import org.springframework.stereotype.Component;

@Component
public class HseOperationFactory implements OperationFactory {

    @Override
    public Operation createOperation(int id, OperationType operationType, int bankAccountId, double amount, int date,
                                     String description, int categoryId) {
        return new HseOperation(id, operationType, bankAccountId, amount, date, description, categoryId);
    }
}
