package hse.kpo.kafka.producers;

import hse.kpo.dto.responses.OperationResponse;
import hse.kpo.dto.responses.PaymentOperationMessageRequest;
import hse.kpo.dto.responses.PaymentOperationMessageResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentOperationMessageResponce> kafkaTemplate;

    public void sendOperation(String topic, PaymentOperationMessageResponce responce) {
        kafkaTemplate.send(topic, responce);
    }
}
