package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PedalEngine implements IEngine {
    private final int size;

    /**
     * Проверяет подходит ли двигатель покупателю
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    /**
     * Создает педальный двигатель
     * @param size
     */
    public PedalEngine(int size) {
        this.size = size;
    }
}
