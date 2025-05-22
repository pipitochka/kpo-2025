package hse.kpo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hse.kpo.domains.sales.ReportSalesObserver;
import hse.kpo.facade.Hse;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.storages.ShipStorage;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




/**
 * Facade test.
 */
@SpringBootTest
public class FacadeTest {
    @Autowired
    Hse hse;

    @Autowired
    HseCarService hseCarService;

    @Autowired
    HseShipService hseShipService;

    @Autowired
    CarStorage carStorage;

    @Autowired
    ShipStorage shipStorage;

    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    HandCarFactory handCarFactory;

    @Autowired
    HandShipFactory handShipFactory;

    @Autowired
    FlyingCarFactory flyingCarFactory;

    @Autowired
    FlyingShipFactory flyingShipFactory;

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Autowired
    PedalShipFactory pedalShipFactory;

    @Autowired
    ReportSalesObserver reportSalesObserver;


    @Test
    @DisplayName("all test")
    void contextTest() throws IOException {
        hse.addCustomer("Ivan1", 6, 4, 150);
        hse.addCustomer("Maksim", 4, 6, 80);
        hse.addCustomer("Petya", 6, 6, 20);
        hse.addCustomer("Nikita", 6, 6, 300);

        hse.addPedalCar(6);
        hse.addPedalCar(6);

        hse.addHandCar();
        hse.addHandWilledCatamarand();

        hse.sell();

        assertThat(customerStorage.getCustomers().get(0).getCar()).isNotNull();
        assertThat(customerStorage.getCustomers().get(1).getCar()).isNotNull();
        assertThat(customerStorage.getCustomers().get(2).getCar()).isNotNull();
        assertThat(customerStorage.getCustomers().get(3).getCar()).isNotNull();


    }
}
