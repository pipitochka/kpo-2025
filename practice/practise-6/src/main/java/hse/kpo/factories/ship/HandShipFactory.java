package hse.kpo.factories.ship;

import hse.kpo.domains.engines.HandEngine;
import hse.kpo.domains.objects.Ship;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of hand ship factory.
 */
@Component
public class HandShipFactory implements ShipFactoryInterface<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new HandEngine();

        return new Ship(engine, shipNumber);
    }
}
