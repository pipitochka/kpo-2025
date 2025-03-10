package hse.domains.object;

import hse.emums.CommandType;
import hse.emums.OperationType;
import hse.interfaces.object.CommandContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class HseCommandContext implements CommandContext {

    @Getter
    public final CommandType commandType;

    @Getter
    @Setter
    private OperationType operationType;

    @Getter
    @Setter
    private double amount;

    @Getter
    @Setter
    private int date;

    @Getter
    @Setter
    private String description = "";

    @Getter
    @Setter
    private int categoryId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int accountId;

}
