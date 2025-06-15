package hse.kpo.kafka.producers;

import hse.kpo.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncomeProducer {

    private final KafkaTemplate<String, Request> kafkaTemplate;

    public void sendOperation(String topic, Request request) {
        kafkaTemplate.send(topic, request);
    }
}
