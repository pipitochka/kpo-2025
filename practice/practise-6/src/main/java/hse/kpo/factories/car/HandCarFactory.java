package hse.kpo.factories.car;

import hse.kpo.domains.engines.HandEngine;
import hse.kpo.domains.objects.Car;
import hse.kpo.interfaces.factories.CarFactoryInterface;
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
