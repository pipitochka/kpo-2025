package hse.kpo.services;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.interfaces.providers.CarProviderInterface;
import hse.kpo.interfaces.providers.CustomerProviderInterface;
import hse.kpo.interfaces.sales.Observable;
import hse.kpo.interfaces.sales.Sales;
import hse.kpo.interfaces.sales.SalesObserver;
import hse.kpo.repositories.CarRepositoryInterface;
import hse.kpo.repositories.CustomerRepositoryInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * class of hse car service.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCarService implements Observable, CarProviderInterface {

    private final List<SalesObserver> observers = new ArrayList<>();

    private final CustomerProviderInterface customerProvider;

    private final CarRepositoryInterface carRepository;

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver() {
        observers.removeLast();
    }

    public void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    private static final Logger logger = LoggerFactory.getLogger(HseCarService.class);


    /**
     * Метод продажи машин.
     */
    @Sales
    public void sellCars() {
        customerProvider.getCustomers().stream()
                .filter(customer -> customer.getCars() == null || customer.getCars().isEmpty())
                .forEach(customer -> {
                    Car car = takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.getCars().add(car); // Добавляем автомобиль в список клиента
                        car.setCustomer(customer);   // Устанавливаем ссылку на клиента в автомобиле
                        carRepository.save(car);     // Сохраняем изменения
                        notifyObserversForSale(customer, ProductionTypes.CAR, car.getVin());
                    } else {
                        log.warn("No car in CarService");
                    }
                });
    }

    @Override
    public Car takeCar(Customer customer) {

        var filteredCars = carRepository.findAll().stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(carRepository::delete);

        return firstCar.orElse(null);
    }

    /**
     * Метод добавления {@link Car} в систему.
     *
     * @param carFactory фабрика для создания автомобилей
     * @param carParams параметры для создания автомобиля
     */
    public <T> Car addCar(CarFactoryInterface<T> carFactory, T carParams) {
        return carRepository.save(carFactory.create(carParams));
    }

    public Car addExistingCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> findByVin(Integer vin) {
        return carRepository.findById(vin);
    }

    public void deleteByVin(Integer vin) {
        carRepository.deleteById(vin);
    }

    public List<Car> getCarsWithFiltration(String engineType, Integer vin) {
        return carRepository.findCarsByEngineTypeAndVinGreaterThan(engineType, vin);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }
}