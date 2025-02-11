package hse.kpo.services;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.interfaces.CarFactoryInterface;
import hse.kpo.interfaces.CarProviderInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * interface of car service.
 */
@Component
public class CarServiceInterface implements CarProviderInterface {

    private final List<Car> cars = new ArrayList<>();

    private int carNumberCounter = 0;

    /**
     * function which gives car to customer.
     *
     * @param customer person who will take a car.
     * @return car witch will be taken.
     */
    @Override
    public Car takeCar(Customer customer) {

        var filteredCars = cars.stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(cars::remove);

        return firstCar.orElse(null);
    }

    /**
     * function witch add cars to the car service.
     *
     * @param carFactory factory which makes car.
     * @param carParams params which used to car constructor.
     * @param <CarParamsT> template of type factory.
     */
    public <CarParamsT> void addCar(CarFactoryInterface<CarParamsT> carFactory, CarParamsT carParams) {
        // создаем автомобиль из переданной фабрики
        var car = carFactory.createCar(
                carParams, // передаем параметры
                ++carNumberCounter // передаем номер - номер будет начинаться с 1
        );

        cars.add(car); // добавляем автомобиль
    }
}