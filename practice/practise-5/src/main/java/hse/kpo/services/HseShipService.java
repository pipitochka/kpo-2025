package hse.kpo.services;

import hse.kpo.interfaces.CarProviderInterface;
import hse.kpo.interfaces.CustomerProviderInterface;
import hse.kpo.interfaces.ShipProviderInterface;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HseShipService {

    private final ShipProviderInterface shipProvider;

    private final CustomerProviderInterface customerProvider;

    public HseShipService(ShipProviderInterface shipProvider, CustomerProviderInterface customersProvider) {
        this.shipProvider = shipProvider;
        this.customerProvider = customersProvider;
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
                    }
                });
    }
}
