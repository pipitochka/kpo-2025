package hse.kpo;

import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;
import hse.kpo.domains.objects.WilledCatamaran;
import hse.kpo.domains.sales.ReportSalesObserver;
import hse.kpo.factories.car.FlyingCarFactory;
import hse.kpo.factories.car.HandCarFactory;
import hse.kpo.factories.car.PedalCarFactory;
import hse.kpo.factories.ship.FlyingShipFactory;
import hse.kpo.factories.ship.HandShipFactory;
import hse.kpo.factories.ship.PedalShipFactory;
import hse.kpo.factories.catamaran.WilledCatamaranFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.records.Report;
import hse.kpo.services.HseCarService;
import hse.kpo.services.HseShipService;
import hse.kpo.storages.CarStorage;
import hse.kpo.storages.CustomerStorage;
import hse.kpo.storages.ShipStorage;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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

    void addCustomer(String name, int legPower, int handPower, int iq){
        Customer customer = new Customer(name, legPower, handPower, iq);
        customerStorage.addCustomer(customer);
    }

    @PostConstruct
    void init(){
        hseCarService.addObserver(reportSalesObserver);
        hseShipService.addObserver(reportSalesObserver);
    }

    void addPedalCar(int pedalSize){
        carStorage.addCar(pedalCarFactory, new PedalEngineParams(pedalSize));
    }

    void addHandCar(){
        carStorage.addCar(handCarFactory, EmptyEngineParams.DEFAULT);
    }

    void addFlyingCar(){
        carStorage.addCar(flyingCarFactory, EmptyEngineParams.DEFAULT);
    }

    void addFlyingShip(){
        shipStorage.addShip(flyingShipFactory, EmptyEngineParams.DEFAULT);
    }

    void addHandShip(){
        shipStorage.addShip(handShipFactory, EmptyEngineParams.DEFAULT);
    }

    void addPedalShip(int pedalSize){
        shipStorage.addShip(pedalShipFactory, new PedalEngineParams(pedalSize));
    }

    void addWilledCatamarand(Ship ship){
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    void addPedalWilledCatamarand(int pedalSize){
        Ship ship = pedalShipFactory.createShip(new PedalEngineParams(pedalSize), 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    void addHandWilledCatamarand(){
        Ship ship = handShipFactory.createShip(EmptyEngineParams.DEFAULT, 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    void addFlyingWilledCatamarand(){
        Ship ship = flyingShipFactory.createShip(EmptyEngineParams.DEFAULT, 0);
        carStorage.addCar(willedCatamaranFactory, ship);
    }

    void sell(){
        hseCarService.sellCars();
        hseShipService.sellShips();
    }



    Report generateReport(){
        return reportSalesObserver.buildReport();
    }
}
