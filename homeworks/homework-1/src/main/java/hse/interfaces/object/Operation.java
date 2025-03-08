package hse.interfaces.object;

import hse.emums.OperationType;

public interface Operation {
    public int getId();
    public OperationType getOperationType();
    public int getBankAccountId();
    public double getAmount();
    public int getDate();
    public String getDescription();
    public int getCategoryId();
}
