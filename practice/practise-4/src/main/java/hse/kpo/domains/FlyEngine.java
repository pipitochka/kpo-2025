package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.ToString;

@ToString
public class FlyEngine implements IEngine {

    /**
     * Проверяет подходит ли двигатель покупателю
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
