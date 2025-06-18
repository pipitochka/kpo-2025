package hse.kpo.dto;

import lombok.Data;

@Data
public class Request {

    private String serviceName = "ORDERS_SHOP";

    private Long id;

    private Long accountId;

    private String operationType = "EXPENSE";

    private double amount;
}
