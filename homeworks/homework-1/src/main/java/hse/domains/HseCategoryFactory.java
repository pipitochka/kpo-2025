package hse.domains;

import hse.emums.OperationType;
import hse.interfaces.Category;
import hse.interfaces.CategoryFactory;

public class HseCategoryFactory implements CategoryFactory {

    @Override
    public Category createCategory(int id, OperationType type, String name) {
        Category newCategory = new HseCategory(id, type, name);
        return newCategory;
    }
}
