package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import java.util.List;

/**
 * customer provider interface.
 */
public interface CustomerProviderInterface {
    List<Customer> getCustomers(); // метод возвращает коллекцию только для чтения,
    // так как мы не хотим давать вызывающему коду возможность изменять список
}
