package hse.kpo.domains;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Customer {
    private final String name;

    private final int legPower;

    private final int handPower;

    @Setter
    private Car car;

    @Getter
    private final int iq;

    /**
     * Конструктор человека без iq
     * @param name
     * @param legPower
     * @param handPower
     * @param iq
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }

    /**
     * Конструктор человека со всеми параметрами
     * @param name
     * @param legPower
     * @param handPower
     */
    public Customer(String name, int legPower, int handPower) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = 0;
    }
}
