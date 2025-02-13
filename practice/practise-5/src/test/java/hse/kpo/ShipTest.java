package hse.kpo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hse.kpo.domains.Customer;
import hse.kpo.domains.ReportBuilder;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.FlyingShipFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.HandShipFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.factories.PedalShipFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.services.ShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * tests of ships.
 */
@SpringBootTest
public class ShipTest {

    @Autowired
    private FlyingShipFactory flyingShipFactory;

    @Autowired
    private HandShipFactory handShipFactory;

    @Autowired
    private PedalShipFactory pedalShipFactory;

    @Autowired
    private ReportBuilder reportBuilder;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private ShipService shipService;

    @Autowired
    private HseShipService hseShipService;

    @Autowired
    private HseCarService hseCarService;

    @Autowired
    private FlyingCarFactory flyingCarFactory;

    @Autowired
    private PedalCarFactory pedalCarFactory;

    @Autowired
    private HandCarFactory handCarFactory;

    @Autowired
    private CarService carService;

    @BeforeEach
    void setUp() {
        reportBuilder = new ReportBuilder();
        customerStorage = new CustomerStorage();
        carService = new CarService();
        shipService = new ShipService();
        hseCarService = new HseCarService(carService, customerStorage);
        hseShipService = new HseShipService(shipService, customerStorage);
    }

    @Test
    @DisplayName("Reports test")
    void shipTest() {
        reportBuilder.addOperation("Initializsation");

        var ship1 = flyingShipFactory.createShip(EmptyEngineParams.DEFAULT, 1);
        var ship2 = handShipFactory.createShip(EmptyEngineParams.DEFAULT, 2);
        var ship3 = pedalShipFactory.createShip(new PedalEngineParams(6), 3);

        assertThat(ship1).isNotNull();
        assertThat(ship2).isNotNull();
        assertThat(ship3).isNotNull();
    }

    @Test
    @DisplayName("Reports test")
    void shipSellTest() {
        reportBuilder.addOperation("Initializsation");

        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Ilya", 6, 6, 320));

        shipService.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(pedalShipFactory, new PedalEngineParams(3));

        reportBuilder.addOperation("add customers")
                .addCustomers(customerStorage.getCustomers());

        hseShipService.sellShips();

        var report = reportBuilder
                .addOperation("selling ships")
                .addCustomers(customerStorage.getCustomers())
                .build();

        System.out.println(report.toString());

        assertThat(report.content().contains("Maksim")).isTrue();
        assertThat(report.content().contains("Petya")).isTrue();
        assertThat(report.content().contains("Ilya")).isTrue();;
        assertThat(report.content().contains("Ship(engine=FlyEngine(), vin=1)")).isTrue();
        assertThat(report.content().contains("Ship(engine=HandEngine(), vin=2)")).isTrue();
        assertThat(report.content().contains("Ship(engine=PedalEngine(size=3), vin=3)")).isTrue();
    }

    @Test
    @DisplayName("Mixed test: cars and ships")
    void shipAndCarsMixTest() {
        reportBuilder.addOperation("Initializsation");

        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Ilya", 6, 6, 320));

        shipService.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipService.addShip(pedalShipFactory, new PedalEngineParams(3));

        carService.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        reportBuilder.addOperation("add customers")
                .addCustomers(customerStorage.getCustomers());

        hseCarService.sellCars();
        hseShipService.sellShips();

        var report = reportBuilder
                .addOperation("selling all")
                .addCustomers(customerStorage.getCustomers())
                .build();

        System.out.println(report.toString());

        assertThat(report.content().contains("Maksim")).isTrue();
        assertThat(report.content().contains("Petya")).isTrue();
        assertThat(report.content().contains("Ilya")).isTrue();;
        assertThat(report.content().contains("Ship(engine=FlyEngine(), vin=1)")).isTrue();
        assertThat(report.content().contains("Ship(engine=HandEngine(), vin=2)")).isTrue();
        assertThat(report.content().contains("Ship(engine=PedalEngine(size=3), vin=3)")).isTrue();
        assertThat(report.content().contains("Car(engine=HandEngine(), vin=3)")).isTrue();
        assertThat(report.content().contains("Car(engine=PedalEngine(size=6), vin=2)")).isTrue();
        assertThat(report.content().contains("Car(engine=FlyEngine(), vin=1)")).isTrue();
    }
}
