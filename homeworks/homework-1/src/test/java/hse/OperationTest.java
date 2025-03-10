package hse;

import hse.domains.object.HseAccount;
import hse.domains.object.HseCategory;
import hse.domains.object.HseOperation;
import hse.emums.OperationType;
import hse.interfaces.object.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationTest {

    @Test
    @DisplayName("OperationTest")
    void operationTest() {
        Account account = new HseAccount(11, "Valya");
        HseCategory hseCategory1 = new HseCategory(1, OperationType.EXPENSE, "Cofe");
        HseCategory hseCategory2 = new HseCategory(2, OperationType.EXPENSE, "Health");
        HseCategory hseCategory3 = new HseCategory(3, OperationType.INCOME, "Salary");
        HseCategory hseCategory4 = new HseCategory(4, OperationType.INCOME, "CashBack");
        HseOperation operation1 = new HseOperation(1, OperationType.INCOME, account, 100, 1, "", hseCategory1);
        HseOperation operation2 = new HseOperation(2, OperationType.INCOME, account, 210, 4, "qwe", hseCategory2);
        HseOperation operation3 = new HseOperation(3, OperationType.INCOME, account, 22.5, 1, "", hseCategory3);
        HseOperation operation4 = new HseOperation(4, OperationType.INCOME, account, 11.5, 5, "qq", hseCategory4);
        HseOperation operation5 = new HseOperation(5, OperationType.INCOME, account, 7, 2, "ww", hseCategory4);
        System.out.println(operation1);
        System.out.println(operation2);
        System.out.println(operation3);
        System.out.println(operation4);
        System.out.println(operation5);
    }
}
