package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.ToString;

/**
 * class of hand engine.
 */
@ToString
public class HandEngineInterface implements EngineInterface {

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
