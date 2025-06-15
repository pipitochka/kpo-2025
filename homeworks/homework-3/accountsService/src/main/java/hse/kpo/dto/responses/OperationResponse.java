package hse.kpo.dto.responses;

import hse.kpo.dto.AccountDto;
import hse.kpo.dto.OperationDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OperationResponse {
    private final boolean success;

    private final OperationDto operation;

    private final String message;
}
