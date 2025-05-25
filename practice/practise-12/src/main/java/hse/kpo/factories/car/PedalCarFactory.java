package hse.kpo.factories.car;

import hse.kpo.domains.engines.PedalEngine;
import hse.kpo.domains.objects.Car;
import hse.kpo.interfaces.factories.CarFactoryInterface;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * class of pedal car factory.
 */
@Component
public class PedalCarFactory implements CarFactoryInterface<PedalEngineParams> {
    @Override
    public Car create(PedalEngineParams carParams) {
        var engine = new PedalEngine(carParams.pedalSize());

        return new Car(engine);
    }
}