package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@RequiredArgsConstructor
public class HseOperation implements Operation {

    @Getter
    private final int id;

    @Getter
    private final OperationType operationType;

    @Getter
    private final int bankAccountId;

    @Getter
    private final double amount;

    @Getter
    private final int date;

    @Getter
    private final String description;

    @Getter
    private final int categoryId;
}
