package hse.kpo.interfaces.providers;

import hse.kpo.domains.objects.Customer;
import hse.kpo.dto.requests.CustomerRequest;

import java.util.List;

/**
 * customer provider interface.
 */
public interface CustomerProviderInterface {
    List<Customer> getCustomers();

    void addCustomer(Customer customer);

    Customer updateCustomer(CustomerRequest customerRequest);

    boolean deleteCustomer(String name);
}
