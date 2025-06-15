package hse.kpo.kafka.consumers;

import hse.kpo.domain.enums.OperationResult;
import hse.kpo.dto.responses.OperationResponse;
import hse.kpo.dto.responses.PaymentOperationMessageRequest;
import hse.kpo.dto.responses.PaymentOperationMessageResponce;
import hse.kpo.kafka.producers.PaymentProducer;
import hse.kpo.services.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {
    private final IAccountService accountService;
    private final PaymentProducer paymentProducer;

    @KafkaListener(topics = "input-operations", groupId = "accounts")
    public void consume(PaymentOperationMessageRequest message) {
        String externalId = message.getId().toString() + message.getServiceName();
        OperationResponse operationResponse = accountService.applyOperation(externalId,
                message.getAccountId(), message.getOperationType(), message.getAmount());
        PaymentOperationMessageResponce paymentOperationMessageResponce = new PaymentOperationMessageResponce();
        paymentOperationMessageResponce.setId(message.getId());
        paymentOperationMessageResponce.setServiceName(message.getServiceName());
        paymentOperationMessageResponce.setResult(operationResponse.isSuccess() ?
                operationResponse.getOperation().getOperationResult() : OperationResult.REJECTED);
        paymentProducer.sendOperation("output-operations", paymentOperationMessageResponce);
    }

}
