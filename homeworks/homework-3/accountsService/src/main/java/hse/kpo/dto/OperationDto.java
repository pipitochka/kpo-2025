package hse.kpo.dto;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.domain.enums.OperationType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OperationDto {

    private final Long id;

    private final String externalId;

    private final Long accountId;

    private final double amount;

    private final OperationType operationType;

    private final OperationResult operationResult;
}
