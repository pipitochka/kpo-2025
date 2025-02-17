package hse.kpo.factories;

import hse.kpo.domains.FlyEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.ShipFactoryInterface;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of flying ship factory.
 */
@Component
public class FlyingShipFactory implements ShipFactoryInterface<EmptyEngineParams> {
    @Override
    public Ship createShip(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new FlyEngine();

        return new Ship(engine, shipNumber);
    }

}
