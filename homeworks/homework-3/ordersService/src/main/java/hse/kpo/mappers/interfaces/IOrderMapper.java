package hse.kpo.mappers.interfaces;

import hse.kpo.domain.objects.interfaces.IOrder;
import hse.kpo.dto.OrderDto;

public interface IOrderMapper {

    public OrderDto toDto(IOrder account);
}
