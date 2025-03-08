package hse;

import hse.domains.object.HseBankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {

    @Test
    @DisplayName("AccountTest")
    void accountTest() {
        HseBankAccount hseBankAccount1 = new HseBankAccount(1, "Artem");
        HseBankAccount hseBankAccount2 = new HseBankAccount(2, "Andrey");
        HseBankAccount hseBankAccount3 = new HseBankAccount(3, "Nastya");
        System.out.println(hseBankAccount1);
        System.out.println(hseBankAccount2);
        System.out.println(hseBankAccount3);
    }
}
