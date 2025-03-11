package hse.interfaces.object;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface Account {
    public String getName();
    public int getId();
    public double getBalance();
    public void setBalance(double balance);
}
