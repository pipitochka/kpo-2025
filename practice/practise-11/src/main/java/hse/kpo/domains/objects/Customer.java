package hse.kpo.domains.objects;

import jakarta.persistence.*;
import lombok.*;

/**
 * class of customer.
 */
@Getter
@Setter
@Entity
@Table(name = "customers")
@NoArgsConstructor
@ToString
public class Customer {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "legPower")
    private int legPower;

    @Column(name = "handPower")
    private int handPower;

    @Column(name = "iq")
    private int iq;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    private Car car;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ship")
    private Ship ship;



    /**
     * Constructor with iq.
     *
     * @param name person name.
     * @param legPower person leg power.
     * @param handPower person hand power.
     * @param iq person iq.
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }

    /**
     * Constructor without iq.
     *
     * @param name person name.
     * @param legPower person leg power.
     * @param handPower person hand power.
     */
    public Customer(String name, int legPower, int handPower) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = 0;
    }
}
