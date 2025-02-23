package hse.kpo.interfaces.engines;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;

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
