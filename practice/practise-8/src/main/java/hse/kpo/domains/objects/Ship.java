package hse.kpo.domains.objects;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import hse.kpo.interfaces.transport.Transport;
import lombok.Getter;
import lombok.ToString;

/**
 * class of ships.
 */
@ToString
public class Ship implements Transport {

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

    @Override
    public String getEngineType() {
        return engine.toString();
    }

    @Override
    public String getTransportType() {
        return "Catamaran";
    }
}
