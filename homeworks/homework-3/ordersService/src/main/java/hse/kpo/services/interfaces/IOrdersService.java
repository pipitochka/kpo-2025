package hse.kpo.services.interfaces;

import hse.kpo.domain.enums.OrderType;
import hse.kpo.dto.OrderDto;
import hse.kpo.dto.Responce;


import java.util.List;

public interface IOrdersService {

    public OrderDto createOrder(Long accountId, double amount, OrderType orderType);

    public List<OrderDto> getAllOrders();

    public void update(Responce responce);
}
