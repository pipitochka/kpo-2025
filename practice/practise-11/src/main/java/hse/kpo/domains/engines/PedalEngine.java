package hse.kpo.domains.engines;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * class of pedal engine.
 */
@Getter
@Entity
@DiscriminatorValue("PEDAL")
@NoArgsConstructor
public class PedalEngine extends AbstractEngine {
    private int size;

    /**
     * Проверяет подходит ли двигатель покупателю.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель.
     */
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes productionTypes) {
        return customer.getLegPower() > 5;
    }

    @Override
    public String toString() {
        return "PEDAL";
    }

    public PedalEngine(int size) {
        this.size = size;
    }
}