package zoo.domains.animals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * class herbo.
 */
@ToString(callSuper = true)
public abstract class Herbo extends Animal {

    @Getter
    @Setter
    private int kindness;

    public Herbo(int food, int number, int kindness) {
        super(food, number);
        this.kindness = kindness;
    }
}
