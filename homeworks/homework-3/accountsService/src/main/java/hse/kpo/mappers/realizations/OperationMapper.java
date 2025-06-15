package hse.kpo.mappers.realizations;

import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.dto.OperationDto;
import hse.kpo.mappers.interfaces.IOperationMapper;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper implements IOperationMapper {
    @Override
    public OperationDto toDto(IOperation operation) {
        return new OperationDto(
                operation.getId(), operation.getExternalId(), operation.getAccountId(), operation.getAmount(),
                operation.getOperationType(), operation.getOperationResult());
    }
}
