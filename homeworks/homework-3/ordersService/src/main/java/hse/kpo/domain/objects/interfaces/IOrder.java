package hse.kpo.domain.objects.interfaces;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.enums.OrderType;

public interface IOrder {

    public double getPrice();

    public Long getCustomerId();

    public Long getId();

    public IncomeStatus getStatus();

    public OrderType getOrderType();

    public void setStatus(IncomeStatus status);
}
