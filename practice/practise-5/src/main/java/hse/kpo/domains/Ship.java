package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.Getter;
import lombok.ToString;

/**
 * class of ships.
 */
@ToString
public class Ship {

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
