package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HseOperation implements Operation {

    @Getter
    private int id;

    @Getter
    private OperationType operationType;

    @Getter
    private int bankAccountId;

    @Getter
    private double amount;

    @Getter
    private int date;

    @Getter
    private String description;

    @Getter
    private int categoryId;
}
