package hse.kpo.interfaces.sales;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;

public interface Observable {
    public void addObserver(SalesObserver observer);
    public void removeObserver();
    public void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin);
}
