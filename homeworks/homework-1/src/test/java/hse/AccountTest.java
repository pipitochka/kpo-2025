package hse;

import hse.domains.object.HseAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class AccountTest {

    @Mock
    private CommandLineRunner run;

    @Test
    @DisplayName("AccountTest")
    void accountTest() {
        HseAccount hseAccount1 = new HseAccount(1, "Artem");
        HseAccount hseAccount2 = new HseAccount(2, "Andrey");
        HseAccount hseAccount3 = new HseAccount(3, "Nastya");
        System.out.println(hseAccount1);
        System.out.println(hseAccount2);
        System.out.println(hseAccount3);
    }
}
