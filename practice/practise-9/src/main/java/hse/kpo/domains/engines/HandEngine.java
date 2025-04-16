package hse.kpo.domains.engines;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import lombok.ToString;

/**
 * class of hand engine.
 */
@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HandEngine implements EngineInterface {

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        return switch (type) {
            case hse.kpo.enums.ProductionTypes.CAR -> customer.getHandPower() > 5;
            case hse.kpo.enums.ProductionTypes.CATAMARAN -> customer.getHandPower() > 2;
            case null, default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }
}
