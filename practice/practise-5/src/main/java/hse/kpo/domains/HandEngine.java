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
    public boolean isCompatible(Customer customer, ProductionTypes product) {
        return customer.getHandPower() > 5;
    }
}
