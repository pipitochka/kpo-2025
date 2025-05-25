package hse.kpo.domains.objects;

import hse.kpo.domains.engines.AbstractEngine;
import hse.kpo.enums.ProductionTypes;
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
 * class of ships.
 */
@Getter
@Setter
@Entity
@Table(name = "ships")
@ToString
@NoArgsConstructor
public class Ship implements Transport {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private AbstractEngine engine;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vin;

    public Ship(AbstractEngine engine) {
        this.engine = engine;
    }


    public Ship(AbstractEngine engine, int vin) {
        this.engine = engine;
        this.vin = vin;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CATAMARAN); // внутри метода просто
        // вызываем соответствующий метод двигателя
    }

    @Override
    public String getEngineType() {
        return engine.toString();
    }

    @Override
    public String getTransportType() {
        return "Catamaran";
    }
}
