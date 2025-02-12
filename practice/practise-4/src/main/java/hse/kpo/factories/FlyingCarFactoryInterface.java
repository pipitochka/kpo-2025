package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.FlyEngineInterface;
import hse.kpo.interfaces.CarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of flying car factory.
 */
@Component
public class FlyingCarFactoryInterface implements CarFactoryInterface<EmptyEngineParams> {

    /**
     * Создает фабрику летающих бибик.
     *
     * @param carParams params which will be used in car constructor.
     * @param carNumber unique number of a car.
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyEngineInterface();

        return new Car(carNumber, engine);
    }
}
