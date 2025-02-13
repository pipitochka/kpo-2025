package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.CarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * class of hand car factory.
 */
@Component
public class HandCarFactory implements CarFactoryInterface<EmptyEngineParams> {

    /**
     * Создает фабрику ручных машин.
     *
     * @param carParams params which will be used in car constructor.
     * @param carNumber unique number of a car.
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
