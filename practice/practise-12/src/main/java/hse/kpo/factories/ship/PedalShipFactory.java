package hse.kpo.factories.ship;

import hse.kpo.domains.engines.PedalEngine;
import hse.kpo.domains.objects.Ship;
import hse.kpo.interfaces.factories.ShipFactoryInterface;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

/**
 * Class of pedal ship factory.
 */
@Component
public class PedalShipFactory implements ShipFactoryInterface<PedalEngineParams> {

    @Override
    public Ship create(PedalEngineParams shipParams) {
        var engine = new PedalEngine(shipParams.pedalSize());
        return new Ship(engine);
    }
}
