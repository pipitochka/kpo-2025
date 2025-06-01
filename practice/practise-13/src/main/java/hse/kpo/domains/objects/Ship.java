package hse.kpo.domains.objects;

import hse.kpo.domains.engines.AbstractEngine;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.transport.Transport;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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
