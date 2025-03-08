package hse.interfaces;

import hse.emums.OperationType;

public interface CategoryFactory {
    public Category createCategory(int id, OperationType type, String name);
}
