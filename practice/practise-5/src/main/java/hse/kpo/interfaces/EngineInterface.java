package hse.kpo.interfaces;

import hse.kpo.domains.Customer;
import hse.kpo.domains.ProductionTypes;

/**
 * interface of engine.
 */
public interface EngineInterface {

    /**
     * Метод для проверки совместимости двигателя с покупателем.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true, если двигатель подходит покупателю
     */
    boolean isCompatible(Customer customer, ProductionTypes product);
}
