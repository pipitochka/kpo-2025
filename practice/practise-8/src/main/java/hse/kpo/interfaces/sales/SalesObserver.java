package hse.kpo.interfaces.sales;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.reports.Report;
import hse.kpo.enums.ProductionTypes;

/**
 * interface of sale observer to provide information.
 */
public interface SalesObserver {

    void onSale(Customer customer, ProductionTypes productType, int vin);

    void checkCustomers();

    public Report buildReport();
}
