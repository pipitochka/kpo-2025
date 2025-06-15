package hse.kpo.kafka.consumers;

import hse.kpo.dto.Responce;
import hse.kpo.services.interfaces.IIncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IncomeConsumer {
    private final IIncomeService incomeService;


    @KafkaListener(topics = "output-operations", groupId = "incomes")
    public void consume(Responce message) {
        if ("INCOME".equals(message.getServiceName())) {
            incomeService.update(message);
        }
    }

}
