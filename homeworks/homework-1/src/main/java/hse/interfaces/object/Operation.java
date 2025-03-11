package hse.interfaces.object;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hse.emums.OperationType;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
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
