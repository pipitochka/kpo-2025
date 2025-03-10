package hse.interfaces.object;

import hse.emums.OperationType;

public interface Operation {
    public int getId();
    public OperationType getOperationType();
    public Account getAccount();
    public double getAmount();
    public int getDate();
    public String getDescription();
    public Category getCategory();
    public void setCategory(Category category);
}
