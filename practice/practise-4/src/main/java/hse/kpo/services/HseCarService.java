package hse.kpo.services;

import hse.kpo.interfaces.CarProviderInterface;
import hse.kpo.interfaces.CustomerProviderInterface;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * class of hse car service.
 */
@Component
public class HseCarService {

    private final CarProviderInterface carProvider;

    private final CustomerProviderInterface customerProvider;

    public HseCarService(CarProviderInterface carProvider, CustomerProviderInterface customersProvider) {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
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
                    }
                });
    }
}