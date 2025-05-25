package hse.kpo.factories.ship;

import hse.kpo.domains.engines.FlyEngine;
import hse.kpo.domains.objects.Ship;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of flying ship factory.
 */
@Component
public class FlyingShipFactory implements ShipFactoryInterface<EmptyEngineParams> {

    @Override
    public Ship create(EmptyEngineParams shipParams) {
        var engine = new FlyEngine();

        return new Ship(engine);
    }

}
