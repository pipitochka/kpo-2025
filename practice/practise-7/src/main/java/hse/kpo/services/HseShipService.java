package hse.kpo.services;

import hse.kpo.interfaces.factories.ShipProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * class of hse ship service.
 */
@Component
public class HseShipService {

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
                        logger.info(customer + " take ship " + car);
                    }
                });
    }
}
