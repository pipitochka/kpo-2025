package hse.kpo.dto.responses;

import hse.kpo.domain.enums.OperationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentOperationMessageRequest {
    private String serviceName;
    private Long id;
    private Long accountId;
    private OperationType operationType;
    private double amount;
}
