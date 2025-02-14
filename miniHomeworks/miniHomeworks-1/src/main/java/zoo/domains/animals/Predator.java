package zoo.domains.animals;

import lombok.ToString;

/**
 * class predator.
 */
@ToString(callSuper = true)
public abstract class Predator extends Animal {

    public Predator(int food, int number) {
        super(food, number);
    }
}
