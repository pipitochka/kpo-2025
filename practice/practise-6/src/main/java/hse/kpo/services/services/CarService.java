package hse.kpo.services.services;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.objects.Customer;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.interfaces.providers.CarProviderInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * class of car service.
 */
@Component
public class CarService implements CarProviderInterface {

    private final List<Car> cars = new ArrayList<>();

    private int carNumberCounter = 0;

    @Override
    public Car takeCar(Customer customer) {

        var filteredCars = cars.stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(cars::remove);

        return firstCar.orElse(null);
    }

    /**
     * function which make car and add it to the pool.
     *
     * @param carFactory factory which will make car.
     * @param carParams car params which used in car constructor.
     * @param <ParamsT> params for constructor.
     */
    public <ParamsT> void addCar(CarFactoryInterface<ParamsT> carFactory, ParamsT carParams) {
        // создаем автомобиль из переданной фабрики
        var car = carFactory.createCar(
                carParams, // передаем параметры
                ++carNumberCounter // передаем номер - номер будет начинаться с 1
        );

        cars.add(car); // добавляем автомобиль
    }
}