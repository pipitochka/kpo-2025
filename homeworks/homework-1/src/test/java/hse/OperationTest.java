package hse;

import hse.domains.HseBankAccount;
import hse.domains.HseOperation;
import hse.emums.OperationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperationTest {

    @Test
    @DisplayName("OperationTest")
    void operationTest() {
        HseOperation operation1 = new HseOperation(1, OperationType.INCOME, 1, 100, 1, "", 1);
        HseOperation operation2 = new HseOperation(2, OperationType.INCOME, 2, 210, 4, "qwe", 2);
        HseOperation operation3 = new HseOperation(3, OperationType.INCOME, 1, 22.5, 1, "", 1);
        HseOperation operation4 = new HseOperation(4, OperationType.INCOME, 3, 11.5, 5, "qq", 4);
        HseOperation operation5 = new HseOperation(5, OperationType.INCOME, 7, 7, 2, "ww", 5);
        System.out.println(operation1);
        System.out.println(operation2);
        System.out.println(operation3);
        System.out.println(operation4);
        System.out.println(operation5);
    }
}
