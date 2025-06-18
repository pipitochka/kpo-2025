package hse.kpo.mappers.realizations;

import hse.kpo.domain.objects.interfaces.IOrder;
import hse.kpo.dto.OrderDto;
import hse.kpo.mappers.interfaces.IOrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements IOrderMapper {

    @Override
    public OrderDto toDto(IOrder order) {
        return new OrderDto(order.getId(), order.getCustomerId(), order.getPrice(),
                order.getStatus(), order.getOrderType());
    }
}
