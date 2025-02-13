package hse.kpo.factories;

import hse.kpo.domains.PedalEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.ShipFactoryInterface;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

@Component
public class PedalShipFactory implements ShipFactoryInterface<PedalEngineParams> {
    @Override
    public Ship createShip(PedalEngineParams shipParams, int shipNumber) {
        var engine = new PedalEngine(shipParams.pedalSize());
        return new Ship(engine, shipNumber);
    }
}
