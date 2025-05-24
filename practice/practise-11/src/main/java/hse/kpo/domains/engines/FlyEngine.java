package hse.kpo.domains.engines;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@DiscriminatorValue("LEVITATION")
@RequiredArgsConstructor
public class FlyEngine extends AbstractEngine {
    private int size;

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes productionTypes) {
        return switch (productionTypes) {
            case hse.kpo.enums.ProductionTypes.CAR -> customer.getHandPower() > 5;
            case hse.kpo.enums.ProductionTypes.CATAMARAN -> customer.getHandPower() > 2;
            case null, default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }

    @Override
    public String toString() {
        return "LEVITATION";
    }
}