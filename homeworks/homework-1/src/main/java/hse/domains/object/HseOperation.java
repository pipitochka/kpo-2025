package hse.domains.object;

import hse.emums.OperationType;
import hse.interfaces.object.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
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
    @Setter
    private int categoryId;

    public HseOperation(int id, OperationType operationType, int bankAccountId, double amount, int date, String description, int categoryId) {
        this.id = id;
        this.operationType = operationType;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
    }
}
