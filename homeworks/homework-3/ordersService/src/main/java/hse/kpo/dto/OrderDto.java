package hse.kpo.dto;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.enums.OrderType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderDto {

    private final Long id;

    private final Long customerId;

    private final double price;

    private final IncomeStatus status;

    private final OrderType orderType;
}
