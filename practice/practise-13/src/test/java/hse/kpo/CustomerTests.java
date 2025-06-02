package hse.kpo;

import hse.kpo.domains.objects.Customer;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * customers tests.
 */
@SpringBootTest
public class CustomerTests {
    @Autowired
    private CustomerProviderInterface customerStorage;

    @BeforeEach
    public void setUp() {
        customerStorage = new CustomerService();
    }


    @Test
    @DisplayName("CustomerStorage")
    void customerStorageTest() {
        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6, 100));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 100));
        Assertions.assertEquals(customerStorage.getCustomers().size(), 3);
    }
}
