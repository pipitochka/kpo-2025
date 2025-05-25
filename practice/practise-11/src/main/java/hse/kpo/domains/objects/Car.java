package hse.kpo.domains.objects;

import hse.kpo.domains.engines.AbstractEngine;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import hse.kpo.interfaces.transport.Transport;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * class of car.
 */
@Getter
@Setter
@Entity
@Table(name = "cars")
@ToString
@NoArgsConstructor
public class Car implements Transport {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vin;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private AbstractEngine engine;

    public Car(int vin, AbstractEngine engine) {
        this.vin = vin;
        this.engine = engine;
    }

    public Car(AbstractEngine engine) {
        this.engine = engine;
    }

    /**
     * Проверяет подходит ли человек машине.
     *
     * @param customer customer who would buy a car.
     * @return true если подходят, false - иначе.
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CAR); // внутри метода
        // просто вызываем соответствующий метод двигателя
    }

    @Override
    public String getEngineType() {
        return engine.toString();
    }

    @Override
    public String getTransportType() {
        return "Car";
    }
}
