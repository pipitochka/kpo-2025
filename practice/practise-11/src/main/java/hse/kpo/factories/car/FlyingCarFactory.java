package hse.kpo.factories.car;

import hse.kpo.domains.engines.FlyEngine;
import hse.kpo.domains.objects.Car;
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
     */
    @Override
    public Car create(EmptyEngineParams carParams) {
        var engine = new FlyEngine();

        return new Car(engine);
    }
}
