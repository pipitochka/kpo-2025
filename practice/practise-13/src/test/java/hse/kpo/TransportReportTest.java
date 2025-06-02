package hse.kpo;

import hse.kpo.domains.sales.ReportSalesObserver;
import hse.kpo.enums.ReportFormat;
import hse.kpo.facade.Hse;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.providers.ShipProviderInterface;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Transport reports test.
 */
@SpringBootTest
public class TransportReportTest {
    @Autowired
    Hse hse;

    @Autowired
    HseCarService hseCarService;

    @Autowired
    HseShipService hseShipService;

    @Autowired
    CarProviderInterface carStorage;

    @Autowired
    ShipProviderInterface shipStorage;

    @Autowired
    CustomerProviderInterface customerStorage;

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


        try (FileWriter fileWriter = new FileWriter("reports/report.md")) {
            hse.transportReport(ReportFormat.MARKDOWN, fileWriter);
        }

        try (FileWriter fileWriter = new FileWriter("reports/report.json")) {
            hse.transportReport(ReportFormat.JSON, fileWriter);
        }

        hse.sell();
        System.out.println(hse.generateReport());

        // Экспорт в консоль в формате Markdown
        hse.exportReport(ReportFormat.MARKDOWN, new PrintWriter(System.out));
        // Экспорт в файл в формате MARKDOWN
        try (FileWriter fileWriter = new FileWriter("reports/report.csv")) {
            hse.transportReport(ReportFormat.CSV, fileWriter);
        }

        // Экспорт в файл в формате JSON
        try (FileWriter fileWriter = new FileWriter("reports/report.xml")) {
            hse.transportReport(ReportFormat.XML, fileWriter);
        }


    }
}
