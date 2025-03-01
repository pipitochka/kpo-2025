package hse.kpo.domains.objects;

import hse.kpo.interfaces.engines.EngineInterface;
import lombok.ToString;

/**
 * class for willed catamaran.
 */
@ToString
public class WilledCatamaran extends Car {

    public WilledCatamaran(Ship ship, int vin) {
        super(vin, ship.getEngine());
    }
}
