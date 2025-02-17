package hse.kpo.domains;

import hse.kpo.interfaces.EngineInterface;
import lombok.Getter;
import lombok.ToString;

/**
 * class of pedal engine.
 */
@ToString
@Getter
public class PedalEngine implements EngineInterface {
    private final int size;

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes productionTypes) {
        return customer.getLegPower() > 5;
    }

    /**
     * Создает педальный двигатель.
     *
     * @param size size of leg of customer.
     */
    public PedalEngine(int size) {
        this.size = size;
    }
}
