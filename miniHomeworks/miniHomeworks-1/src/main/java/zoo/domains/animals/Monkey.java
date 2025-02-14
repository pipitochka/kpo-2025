package zoo.domains.animals;

import lombok.ToString;

/**
 * class monkey.
 */

public class Monkey extends Herbo {

    public Monkey(int food, int number, int kindness) {
        super(food, number, kindness);
    }

    @Override
    public String toString() {
        return "Monkey " + super.getFood() + " " + super.getNumber() + " " + super.getKindness();
    }
}
