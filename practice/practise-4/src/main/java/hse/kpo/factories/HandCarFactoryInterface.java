package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngineInterface;
import hse.kpo.interfaces.CarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of hand car factory.
 */
@Component
public class HandCarFactoryInterface implements CarFactoryInterface<EmptyEngineParams> {

    /**
     * Создает фабрику ручных машин.
     *
     * @param carParams parameters witch will be used for car constructor.
     * @param carNumber unique number of a car.
     * @return car made car.
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new HandEngineInterface(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
