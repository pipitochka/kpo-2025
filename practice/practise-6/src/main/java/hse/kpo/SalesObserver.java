package hse.kpo;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;

public interface SalesObserver {

    void onSale(Customer customer, ProductionTypes productType, int vin);

    void checkCustomers();
}
