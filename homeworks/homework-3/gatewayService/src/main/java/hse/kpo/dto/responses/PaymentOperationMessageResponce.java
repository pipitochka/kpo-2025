package hse.kpo.dto.responses;

import hse.kpo.domain.enums.OperationResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentOperationMessageResponce {

    String serviceName;

    Long id;

    OperationResult result;
}
