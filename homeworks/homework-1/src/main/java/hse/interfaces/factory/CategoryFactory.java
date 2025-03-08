package hse.interfaces.factory;

import hse.emums.OperationType;
import hse.interfaces.object.Category;

public interface CategoryFactory {
    public Category createCategory(int id, OperationType type, String name);
}
