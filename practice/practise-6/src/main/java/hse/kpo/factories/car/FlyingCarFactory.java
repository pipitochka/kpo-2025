package hse.kpo.factories.car;

import hse.kpo.domains.objects.Car;
import hse.kpo.domains.engines.FlyEngine;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of flying car factory.
 */
@Component
public class FlyingCarFactory implements CarFactoryInterface<EmptyEngineParams> {

    /**
     * Создает фабрику летающих бибик.
     *
     * @param carParams params which will be used in car constructor.
     * @param carNumber unique number of a car.
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyEngine();

        return new Car(carNumber, engine);
    }
}
