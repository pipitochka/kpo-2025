package hse.kpo.dto;

import hse.kpo.domain.enums.IncomeStatus;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class IncomeDto {

    private Long id;

    private Long userId;

    private double amount;

    private IncomeStatus status;
}
