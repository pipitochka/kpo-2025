package hse.interfaces.object;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import hse.emums.OperationType;

/**
 * interface of category.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

public interface Category {

    public int getId();

    public String getName();

    public OperationType getOperationType();
}
