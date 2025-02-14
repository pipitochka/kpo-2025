package zoo.domains.animals;

import lombok.ToString;

/**
 * class of wolf.
 */
public class Wolf extends Predator {
    public Wolf(int food, int number) {
        super(food, number);
    }

    @Override
    public String toString() {
        return "Wolf " + super.getFood() + " " + super.getNumber();
    }

}
