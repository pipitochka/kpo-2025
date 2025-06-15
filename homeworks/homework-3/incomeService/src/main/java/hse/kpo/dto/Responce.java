package hse.kpo.dto;

import hse.kpo.domain.enums.IncomeStatus;
import lombok.Data;

@Data
public class Responce {

    String serviceName;

    Long id;

    IncomeStatus result;
}
