package hse.kpo.facade;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;
import hse.kpo.domains.sales.ReportSalesObserver;
import hse.kpo.enums.ReportFormat;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.catamaran.WilledCatamaranFactory;
import hse.kpo.factories.report.ReportExporterFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.interfaces.reports.ReportExporter;
import hse.kpo.interfaces.sales.SalesObserver;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.domains.reports.Report;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.storages.ShipStorage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Writer;


/**
 * facade for a program.
 */
@Component
@RequiredArgsConstructor
public class Hse {

    private final CustomerStorage customerStorage;
    private final HseCarService hseCarService;
    private final HseShipService hseShipService;
    private final PedalCarFactory pedalCarFactory;
    private final PedalShipFactory pedalShipFactory;
    private final HandCarFactory handCarFactory;
    private final HandShipFactory handShipFactory;
    private final FlyingCarFactory flyingCarFactory;
    private final FlyingShipFactory flyingShipFactory;
    private final ShipStorage shipStorage;
    private final CarStorage carStorage;
    private final ReportSalesObserver reportSalesObserver;
    private final WilledCatamaranFactory willedCatamaranFactory;
    private final ReportExporterFactory reportExporterFactory;
    private final SalesObserver salesObserver;

    public void addCustomer(String name, int legPower, int handPower, int iq) {
        Customer customer = new Customer(name, legPower, handPower, iq);
        customerStorage.addCustomer(customer);
    }

    @PostConstruct
    public void init() {
        hseCarService.addObserver(reportSalesObserver);
        hseShipService.addObserver(reportSalesObserver);
    }

    public void addPedalCar(int pedalSize) {

        carStorage.addCar(pedalCarFactory, new PedalEngineParams(pedalSize));
    }

    public void addHandCar() {
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
    }

    public void addFlyingCar() {
        carStorage.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);
    }

    public void addFlyingShip() {
        shipStorage.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
    }

    public void addHandShip() {
        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
    }

    public void addPedalShip(int pedalSize) {
        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(pedalSize));
    }

    public void addWilledCatamarand(Ship ship) {
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    public  void addPedalWilledCatamarand(int pedalSize) {
        Ship ship = pedalShipFactory.createShip(new PedalEngineParams(pedalSize), 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    public void addHandWilledCatamarand() {
        Ship ship = handShipFactory.createShip(EmptyEngineParams.DEFAULT, 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    public void addFlyingWilledCatamarand() {
        Ship ship = flyingShipFactory.createShip(EmptyEngineParams.DEFAULT, 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    public void sell() {
        hseCarService.sellCars();
        hseShipService.sellShips();
    }

    public Report generateReport() {
        return reportSalesObserver.buildReport();
    }

    public void exportReport(ReportFormat format, Writer writer) {
        Report report = salesObserver.buildReport();
        ReportExporter exporter = reportExporterFactory.create(format);

        try {
            exporter.export(report, writer);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
