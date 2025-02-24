package hse.kpo.domains.objects;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import lombok.Getter;
import lombok.ToString;

/**
 * class of ships.
 */
@ToString
public class Ship {

    @Getter
    private final EngineInterface engine;

    @Getter
    private final int vin;

    public Ship(EngineInterface engine, int vin) {
        this.engine = engine;
        this.vin = vin;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CATAMARAN); // внутри метода просто
        // вызываем соответствующий метод двигателя
    }
}
