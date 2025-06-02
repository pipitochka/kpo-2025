package hse.kpo;

import static org.mockito.Mockito.verify;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.sales.ReportSalesObserver;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.providers.ShipProviderInterface;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
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
    private CarProviderInterface carStorage;

    @Autowired
    private ShipProviderInterface shipStorage;

    @Autowired
    private CustomerProviderInterface customerStorage;

    @Autowired
    private FlyingCarFactory flyingCarFactory;

    @Autowired
    private FlyingShipFactory flyingShipFactory;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private HseShipService hseShipService;

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Autowired
    private PedalShipFactory pedalShipFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private HandShipFactory handShipFactory;

    @Autowired
    private ReportSalesObserver reportSalesObserver;

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoadsCar() {
        hseCarService.addObserver(reportSalesObserver);

        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6, 100));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 100));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 100));
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

    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoadsCatamaran() {
        hseShipService.addObserver(reportSalesObserver);

        customerStorage.addCustomer(new Customer("Ivan1", 6, 4, 100));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6, 100));
        customerStorage.addCustomer(new Customer("Petya", 6, 6, 100));
        customerStorage.addCustomer(new Customer("Nikita", 4, 4, 100));
        customerStorage.addCustomer(new Customer("Artem", 4, 4, 350));

        shipStorage.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);

        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(6));
        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(6));

        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        hseShipService.sellShips();

        customerStorage.getCustomers().stream().map(Customer::toString).forEach(System.out::println);

        System.out.println(reportSalesObserver.buildReport());
    }
}