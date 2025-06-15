package hse.kpo.domain.interfaces.factories;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.domain.realizations.objects.Account;

public interface IOperationFactory {

    public IOperation createOperation(String externalId, Account account,
                                      OperationType operationType,  double amount);
}
