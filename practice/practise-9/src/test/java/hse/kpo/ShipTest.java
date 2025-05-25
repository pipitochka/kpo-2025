package hse.kpo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hse.kpo.builders.ReportBuilder;
import hse.kpo.domains.objects.Customer;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.storages.ShipStorage;
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
    private ShipStorage shipStorage;

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
    private CarStorage carStorage;

    @BeforeEach
    void setUp() {
        reportBuilder = new ReportBuilder();
        customerStorage = new CustomerStorage();
        carStorage = new CarStorage();
        shipStorage = new ShipStorage();
        hseCarService = new HseCarService(carStorage, customerStorage);
        hseShipService = new HseShipService(shipStorage, customerStorage);
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

        shipStorage.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(3));

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
        assertThat(report.content().contains("Ship(engine=LEVITATION, vin=1)")).isTrue();
        assertThat(report.content().contains("Ship(engine=HAND, vin=2)")).isTrue();
        assertThat(report.content().contains("Ship(engine=PEDAL, vin=3)")).isTrue();
    }

    @Test
    @DisplayName("Mixed test: cars and ships")
    void shipAndCarsMixTest() {
        reportBuilder.addOperation("Initializsation");

        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Ilya", 6, 6, 320));

        shipStorage.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(3));

        carStorage.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);
        carStorage.addCar(pedalCarFactory, new PedalEngineParams(6));
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

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
        assertThat(report.content().contains("Ship(engine=LEVITATION, vin=1)")).isTrue();
        assertThat(report.content().contains("Ship(engine=HAND, vin=2)")).isTrue();
        assertThat(report.content().contains("Ship(engine=PEDAL, vin=3)")).isTrue();
        assertThat(report.content().contains("Car(engine=HAND, vin=3)")).isTrue();
        assertThat(report.content().contains("Car(engine=PEDAL, vin=2)")).isTrue();
        assertThat(report.content().contains("Car(engine=LEVITATION, vin=1)")).isTrue();
    }
}
