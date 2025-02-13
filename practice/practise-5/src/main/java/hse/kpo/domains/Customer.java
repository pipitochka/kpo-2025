package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * class of customer.
 */
@Getter
@ToString
public class Customer {
    private final String name;

    private final int legPower;

    private final int handPower;

    @Setter
    @Getter
    private Car car;

    @Setter
    @Getter
    private Ship ship;

    @Getter
    private final int iq;

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
