package hse.kpo;

import static org.mockito.Mockito.verify;

import hse.kpo.domains.objects.Customer;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.services.CarStorage;
import hse.kpo.services.storage.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * full application test.
 */
@SpringBootTest
class ReportObserverTest {

    @Autowired
    private CarStorage carStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private FlyingCarFactory flyingCarFactory;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private ReportSalesObserver reportSalesObserver;

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoads() {
        hseCarService.addObserver(reportSalesObserver);

        customerStorage.addCustomer(new Customer("Ivan1", 6, 4));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4));
        customerStorage.addCustomer(new Customer("Artem", 4, 4, 350));

        carStorage.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);

        carStorage.addCar(pedalCarFactory, new PedalEngineParams(6));
        carStorage.addCar(pedalCarFactory, new PedalEngineParams(6));

        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        hseCarService.sellCars();

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        System.out.println(reportSalesObserver.buildReport());
    }
}