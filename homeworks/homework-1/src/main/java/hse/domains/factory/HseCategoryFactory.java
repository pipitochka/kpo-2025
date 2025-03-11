package hse.domains.factory;

import hse.domains.object.HseCategory;
import hse.emums.OperationType;
import hse.interfaces.factory.CategoryFactory;
import hse.interfaces.object.Category;
import org.springframework.stereotype.Component;

/**
 * Factory to makes categories.
 */
@Component
public class HseCategoryFactory implements CategoryFactory {

    @Override
    public Category createCategory(int id, OperationType type, String name) {
        Category newCategory = new HseCategory(id, type, name);
        return newCategory;
    }
}
