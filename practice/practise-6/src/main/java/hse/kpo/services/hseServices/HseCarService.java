package hse.kpo.services.hseServices;

import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * class of hse car service.
 */
@Component
public class HseCarService {

    private static final Logger logger = LoggerFactory.getLogger(HseCarService.class);

    private final CarProviderInterface carProvider;

    private final CustomerProviderInterface customerProvider;

    public HseCarService(CarProviderInterface carProvider, CustomerProviderInterface customersProvider) {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
        logger.info("Hse Car Service created");
    }

    /**
     * function to sell all cars in cars pull to all sellers from sellers poll.
     */
    public void sellCars() {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                        logger.info(customer + " take car " + car);
                    }
                });
    }
}