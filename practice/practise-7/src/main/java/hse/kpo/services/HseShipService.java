package hse.kpo.services;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.factories.ShipProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.sales.Observable;
import hse.kpo.interfaces.sales.SalesObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * class of hse ship service.
 */
@Component
public class HseShipService implements Observable {

    private final List<SalesObserver> observers = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(HseShipService.class);

    private final ShipProviderInterface shipProvider;

    private final CustomerProviderInterface customerProvider;

    /**
     * constructor.
     *
     * @param shipProvider ship provider annotated with this hse car service.
     * @param customersProvider customer provide annotated with this hse car service.
     */
    public HseShipService(ShipProviderInterface shipProvider, CustomerProviderInterface customersProvider) {
        this.shipProvider = shipProvider;
        this.customerProvider = customersProvider;
        logger.info("Hse Ship Service created");
    }

    /**
     * function to sell all cars in cars pull to all sellers from sellers poll.
     */
    public void sellShips() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getShip()))
                .forEach(customer -> {
                    var car = shipProvider.takeShip(customer);
                    if (Objects.nonNull(car)) {
                        customer.setShip(car);
                        notifyObserversForSale(customer, ProductionTypes.CATAMARAN, car.getVin());
                        logger.info(customer + " take ship " + car);
                    }
                });
    }

    @Override
    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver() {
        observers.removeLast();
    }

    @Override
    public void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }
}
