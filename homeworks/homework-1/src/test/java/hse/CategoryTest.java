package hse;

import hse.domains.object.HseCategory;
import hse.emums.OperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * class to test categories.
 */
@SpringBootTest
public class CategoryTest {
    @Test
    @DisplayName("CategoryTest")
    public void categoryTest() {
        HseCategory hseCategory1 = new HseCategory(1, OperationType.EXPENSE, "Cofe");
        HseCategory hseCategory2 = new HseCategory(2, OperationType.EXPENSE, "Health");
        HseCategory hseCategory3 = new HseCategory(3, OperationType.INCOME, "Salary");
        HseCategory hseCategory4 = new HseCategory(4, OperationType.INCOME, "CashBack");
        System.out.println(hseCategory1);
        System.out.println(hseCategory2);
        System.out.println(hseCategory3);
        System.out.println(hseCategory4);
    }
}
