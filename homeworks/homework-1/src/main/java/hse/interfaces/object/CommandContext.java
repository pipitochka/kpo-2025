package hse.interfaces.object;

import hse.emums.CommandType;
import hse.emums.OperationType;

public interface CommandContext {
    public CommandType getCommandType();
    public String getName();
    public double getAmount();
    public OperationType getOperationType();
    public int getAccountId();
    public int getDate();
    public String getDescription();
    public int getCategoryId();
}
