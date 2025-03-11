package hse.domains.object;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hse.emums.OperationType;
import hse.interfaces.object.Account;
import hse.interfaces.object.Category;
import hse.interfaces.object.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonDeserialize(as = HseOperation.class)
@ToString
public class HseOperation implements Operation {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private OperationType operationType;

    @Getter
    @Setter
    private Account account;

    @Getter
    @Setter
    private double amount;

    @Getter
    @Setter
    private int date;

    @Setter
    @Getter
    private String description;

    @Getter
    @Setter
    private Category category;

    public HseOperation(int id, OperationType operationType, Account account, double amount, int date, String description, Category category) {
        this.id = id;
        this.operationType = operationType;
        this.account = account;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public HseOperation() {}
}
