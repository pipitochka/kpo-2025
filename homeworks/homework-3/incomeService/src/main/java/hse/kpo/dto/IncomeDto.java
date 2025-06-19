package hse.kpo.dto;

import hse.kpo.domain.enums.IncomeStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IncomeDto {

    private final Long id;

    private final Long userId;

    private final double amount;

    private final IncomeStatus status;
}
