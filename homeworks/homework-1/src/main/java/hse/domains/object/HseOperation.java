package hse.domains.object;

import hse.emums.OperationType;
import hse.interfaces.object.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
