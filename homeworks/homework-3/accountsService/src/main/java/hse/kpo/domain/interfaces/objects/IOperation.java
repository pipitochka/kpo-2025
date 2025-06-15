package hse.kpo.domain.interfaces.objects;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;

public interface IOperation {

    public Long getId();

    public String getExternalId();

    public Long getAccountId();

    public OperationType getOperationType();

    public OperationResult getOperationResult();

    public void setOperationResult(OperationResult operationResult);

    public double getAmount();
}
