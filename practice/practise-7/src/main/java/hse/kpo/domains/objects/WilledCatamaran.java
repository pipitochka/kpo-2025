package hse.kpo.domains.objects;

import hse.kpo.interfaces.engines.EngineInterface;
import lombok.ToString;

@ToString
public class WilledCatamaran extends Car{

    public WilledCatamaran(Ship ship, int vin) {
        super(vin, ship.getEngine());
    }
}
