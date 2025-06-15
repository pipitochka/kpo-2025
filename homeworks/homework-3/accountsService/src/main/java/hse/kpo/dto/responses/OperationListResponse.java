package hse.kpo.dto.responses;

import hse.kpo.dto.OperationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class OperationListResponse {
    private final boolean success;

    private final List<OperationDto> operations;

    private final String message;
}
