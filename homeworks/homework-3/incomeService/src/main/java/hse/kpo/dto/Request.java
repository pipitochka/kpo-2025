package hse.kpo.dto;

import lombok.Data;

@Data
public class Request {
    private String serviceName = "INCOME";
    private Long id;
    private Long accountId;
    private String operationType = "INCOME";
    private double amount;
}
