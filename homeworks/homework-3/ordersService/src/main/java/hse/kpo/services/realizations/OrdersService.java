package hse.kpo.services.realizations;

import hse.kpo.domain.enums.IncomeStatus;
import hse.kpo.domain.enums.OrderType;
import hse.kpo.domain.objects.interfaces.IOrder;
import hse.kpo.domain.objects.realiations.Order;
import hse.kpo.dto.OrderDto;
import hse.kpo.dto.Request;
import hse.kpo.dto.Responce;
import hse.kpo.kafka.producers.IncomeProducer;
import hse.kpo.mappers.interfaces.IOrderMapper;
import hse.kpo.repositories.IOrdersRepository;
import hse.kpo.services.interfaces.IOrdersService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Log4j2
@Getter
public class OrdersService implements IOrdersService {

    public final IOrderMapper orderMapper;

    private final IOrdersRepository ordersRepository;

    private final IncomeProducer incomeProducer;

    @Transactional
    @Override
    public OrderDto createOrder(Long accountId, double amount, OrderType orderType) {
        Order order = new Order();
        order.setPrice(amount);
        order.setStatus(IncomeStatus.PENDING);
        order.setCustomerId(accountId);
        order.setOrderType(orderType);
        ordersRepository.save(order);
        Request request = new Request();
        request.setAmount(amount);
        request.setAccountId(accountId);
        request.setId(order.getId());
        incomeProducer.sendOperation("input-operations", request);
        return orderMapper.toDto(order);
    }

    @Transactional
    @Override
    public List<OrderDto> getAllOrders() {
        return ordersRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(Responce responce) {
        var possibleOrder = ordersRepository.findById(responce.getId());
        if (possibleOrder.isPresent()) {
            Order order = possibleOrder.get();
            order.setStatus(responce.getResult());
            ordersRepository.save(order);
        }
    }
}
