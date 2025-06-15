package hse.kpo.domain.realizations.factories;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import hse.kpo.domain.interfaces.factories.IOperationFactory;
import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.domain.realizations.objects.Account;
import hse.kpo.domain.realizations.objects.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationFactory implements IOperationFactory {

    @Override
    public Operation createOperation(String externalId, Account account, OperationType operationType, double amount) {
        Operation operation = new Operation();
        operation.setOperationType(operationType);
        operation.setAccount(account);
        operation.setAmount(amount);
        operation.setExternalId(externalId);
        operation.setOperationResult(OperationResult.PENDING);
        return operation;
    }
}
