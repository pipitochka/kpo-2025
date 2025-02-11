package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.FlyEngineInterface;
import hse.kpo.factories.FlyingCarFactoryInterface;
import hse.kpo.factories.HandCarFactoryInterface;
import hse.kpo.factories.PedalCarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarServiceInterface;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * all application tests.
 */
@SpringBootTest
class KpoApplicationTests {

    @Autowired
    private CarServiceInterface carService;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private FlyingCarFactoryInterface flyingCarFactory;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private PedalCarFactoryInterface pedalCarFactory;

    @Autowired
    private HandCarFactoryInterface handCarFactory;

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {
        customerStorage.addCustomer(new Customer("Ivan1", 6, 4));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4));
        customerStorage.addCustomer(new Customer("Artem", 4, 4, 350));

        carService.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);

        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        hseCarService.sellCars();

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);
    }
}