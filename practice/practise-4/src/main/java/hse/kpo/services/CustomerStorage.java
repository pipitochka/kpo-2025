package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.interfaces.CustomerProviderInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * class customer storage.
 */
@Component
public class CustomerStorage implements CustomerProviderInterface {
    private List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
