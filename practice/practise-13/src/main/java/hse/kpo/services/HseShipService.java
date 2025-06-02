package hse.kpo.services;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import hse.kpo.domains.objects.Ship;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.providers.ShipProviderInterface;
import hse.kpo.interfaces.sales.Observable;
import hse.kpo.interfaces.sales.Sales;
import hse.kpo.interfaces.sales.SalesObserver;
import hse.kpo.repositories.CustomerRepositoryInterface;
import hse.kpo.repositories.ShipRepositoryInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * class of hse ship service.
 */
@Component
@RequiredArgsConstructor
public class HseShipService implements Observable, ShipProviderInterface {

    public void clear(){
        shipRepository.deleteAll();
    }

    private final List<SalesObserver> observers = new ArrayList<>();

    private final CustomerProviderInterface customerProvider;

    private final ShipRepositoryInterface shipRepository;

    private static final Logger log = LoggerFactory.getLogger(HseShipService.class);

    @Override
    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver() {
        observers.removeLast();
    }

    @Override
    public void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    /**
     * Метод продажи машин.
     */
    @Sales
    public void sellShips() {
        customerProvider.getCustomers().stream()
                .filter(customer -> customer.getCars() == null || customer.getCars().isEmpty())
                .forEach(customer -> {
                    Ship car = takeShip(customer);
                    if (Objects.nonNull(car)) {
                        customer.getShips().add(car); // Добавляем автомобиль в список клиента
                        car.setCustomer(customer);   // Устанавливаем ссылку на клиента в автомобиле
                        shipRepository.save(car);     // Сохраняем изменения
                        notifyObserversForSale(customer, ProductionTypes.CAR, car.getVin());
                    } else {
                        log.warn("No car in CarService");
                    }
                });
    }


    @Override
    public Ship takeShip(Customer customer) {

        var filteredCars = shipRepository.findAll().stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(shipRepository::delete);

        return firstCar.orElse(null);
    }

    /**
     * Метод добавления {@link Ship} в систему.
     *
     * @param carFactory фабрика для создания автомобилей
     * @param carParams параметры для создания автомобиля
     */
    public <T> Ship addShip(ShipFactoryInterface<T> carFactory, T carParams) {
        return shipRepository.save(carFactory.create(carParams));
    }

    public Ship addExistingShip(Ship car) {
        return shipRepository.save(car);
    }

    public Optional<Ship> findByVin(Integer vin) {
        return shipRepository.findById(vin);
    }

    public void deleteByVin(Integer vin) {
        shipRepository.deleteById(vin);
    }

    public List<Ship> getShipsWithFiltration(String engineType, Integer vin) {
        return shipRepository.findShipByEngineTypeAndVinGreaterThan(engineType, vin);
    }

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }
}
