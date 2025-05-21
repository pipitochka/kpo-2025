package hse.kpo.services;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.sales.Observable;
import hse.kpo.interfaces.sales.Sales;
import hse.kpo.interfaces.sales.SalesObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * class of hse car service.
 */
@Component
public class HseCarService implements Observable {

    private final List<SalesObserver> observers = new ArrayList<>();

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver() {
        observers.removeLast();
    }

    public void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    private static final Logger logger = LoggerFactory.getLogger(HseCarService.class);

    private final CarProviderInterface carProvider;

    public List<Car> takeCars;

    private final CustomerProviderInterface customerProvider;

    /**
     * constructor.
     *
     * @param carProvider car provider annotated with this hse car service.
     * @param customersProvider customer provide annotated with this hse car service.
     */
    public HseCarService(CarProviderInterface carProvider, CustomerProviderInterface customersProvider) {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
        logger.info("Hse Car Service created");
    }

    /**
     * function to sell all cars in cars pull to all sellers from sellers poll.
     */
    @Sales
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                        notifyObserversForSale(customer, ProductionTypes.CAR, car.getVin());
                        logger.info(customer + " take car " + car);
                    }
                });
    }
}