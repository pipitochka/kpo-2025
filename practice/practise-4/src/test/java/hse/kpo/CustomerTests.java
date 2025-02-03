package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.services.CustomerStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTests {
    @Autowired
    private CustomerStorage customerStorage;

    @Test
    @DisplayName("CustomerStorage")
    void CustomerStorageTest() {
        customerStorage.addCustomer(new Customer("Ivan1",6,4));
        customerStorage.addCustomer(new Customer("Maksim",4,6));
        customerStorage.addCustomer(new Customer("Petya",6,6));
        Assertions.assertEquals(customerStorage.getCustomers().size(), 3);
    }
}
