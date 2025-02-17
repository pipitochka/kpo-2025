package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.ToString;

/**
 * class of hand engine.
 */
@ToString
public class HandEngine implements EngineInterface {

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        return switch (type) {
            case hse.kpo.domains.ProductionTypes.CAR -> customer.getHandPower() > 5;
            case hse.kpo.domains.ProductionTypes.CATAMARAN -> customer.getHandPower() > 2;
            case null, default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }
}
