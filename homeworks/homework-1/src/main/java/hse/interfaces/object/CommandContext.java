package hse.interfaces.object;

import hse.emums.CommandType;
import hse.emums.OperationType;

public interface CommandContext {
    public CommandType getCommandType();
    public String getName();
    public double getAmount();
    public OperationType getOperationType();
    public void setOperationType(OperationType operationType);
    public Account getAccount();
    public int getDate();
    public String getDescription();
    public Category getCategory();
}
