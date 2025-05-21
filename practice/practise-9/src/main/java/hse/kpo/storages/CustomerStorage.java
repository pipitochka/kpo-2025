package hse.kpo.storages;

import hse.kpo.domains.objects.Customer;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * class of customer storage.
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
