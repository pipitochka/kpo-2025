package hse.kpo.domains.engines;

import hse.kpo.domains.objects.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.engines.EngineInterface;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * class of abstract engine.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "engine_type")
@Table(name = "engines")
public class AbstractEngine implements EngineInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "engine_type", insertable = false, updatable = false)
    private String type; // Автоматически заполняется дискриминатором


    @Override
    public boolean isCompatible(Customer customer, ProductionTypes product) {
        return false;
    }
}