package hse.kpo.mappers.interfaces;

import hse.kpo.domain.interfaces.objects.IOperation;
import hse.kpo.dto.OperationDto;

public interface IOperationMapper {

    public OperationDto toDto(IOperation operation);
}
