package hse.kpo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hse.kpo.domains.Customer;
import hse.kpo.domains.ReportBuilder;
import hse.kpo.factories.FlyingCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * tests of reports.
 */
@SpringBootTest
public class ReportTests {

    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    HseCarService hseCarService;

    @Autowired
    CarService carService;

    @Autowired
    HandCarFactory handCarFactory;

    @Autowired
    PedalCarFactory pedalCarFactory;

    @Autowired
    FlyingCarFactory flyingCarFactory;

    @Autowired
    ReportBuilder reportBuilder;

    @BeforeEach
    void setUp() {
        carService = new CarService();
        customerStorage = new CustomerStorage();
        hseCarService = new HseCarService(carService, customerStorage);
    }

    @Test
    @DisplayName("Reports test")
    public void reportTest() {
        customerStorage.addCustomer(new Customer("Ivan", 6, 4));
        customerStorage.addCustomer(new Customer("Maksim", 4, 6));
        customerStorage.addCustomer(new Customer("Petya", 6, 6));
        customerStorage.addCustomer(new Customer("Ilya", 6, 6, 320));

        reportBuilder.addOperation("Инициализация системы")
                .addCustomers(customerStorage.getCustomers());

        carService.addCar(handCarFactory, EmptyEngineParams.DEFAULT);

        carService.addCar(pedalCarFactory, new PedalEngineParams(6));
        carService.addCar(pedalCarFactory, new PedalEngineParams(6));

        carService.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);

        hseCarService.sellCars();

        var report = reportBuilder
                .addOperation("Продажа автомобилей")
                .addCustomers(customerStorage.getCustomers())
                .build();

        System.out.println(report.toString());

        assertThat(report.content().contains("Ivan")).isTrue();
        assertThat(report.content().contains("Maksim")).isTrue();
        assertThat(report.content().contains("Petya")).isTrue();
        assertThat(report.content().contains("Ilya")).isTrue();;
        assertThat(report.content().contains("Car(engine=PedalEngine(size=6), vin=3)")).isTrue();
        assertThat(report.content().contains("Car(engine=PedalEngine(size=6), vin=2)")).isTrue();
        assertThat(report.content().contains("Car(engine=HandEngine(), vin=1)")).isTrue();
        assertThat(report.content().contains("Car(engine=FlyEngine(), vin=4)")).isTrue();

    }
}
