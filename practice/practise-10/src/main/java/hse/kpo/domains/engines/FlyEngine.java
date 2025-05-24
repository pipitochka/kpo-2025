package hse.kpo.domains.engines;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import lombok.ToString;

/**
 * class of flying engine.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FlyEngine implements EngineInterface {

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes productionTypes) {
        return customer.getIq() > 300;
    }

    @Override
    public String toString() {
        return "LEVITATION";
    }
}
