package hse.kpo.kafka.consumers;

import hse.kpo.dto.Responce;
import hse.kpo.services.interfaces.IOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IncomeConsumer {
    private final IOrdersService incomeService;


    @KafkaListener(topics = "output-operations", groupId = "decreases")
    public void consume(Responce message) {
        if ("ORDERS_SHOP".equals(message.getServiceName())) {
            incomeService.update(message);
        }
    }

}
