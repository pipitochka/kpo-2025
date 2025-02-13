package hse.kpo.factories;

import hse.kpo.domains.HandEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.ShipFactoryInterface;
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
