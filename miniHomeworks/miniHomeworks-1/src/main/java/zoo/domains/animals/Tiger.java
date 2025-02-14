package zoo.domains.animals;

import lombok.ToString;

/**
 * class tiger.
 */
public class Tiger extends Predator {
    public Tiger(int food, int number) {
        super(food, number);
    }

    @Override
    public String toString() {
        return "Tiger " + super.getFood() + " " + super.getNumber();
    }
}
