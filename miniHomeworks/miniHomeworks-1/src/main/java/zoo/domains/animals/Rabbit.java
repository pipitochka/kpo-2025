package zoo.domains.animals;

import lombok.ToString;

/**
 * class of rabbit.
 */
public class Rabbit extends Herbo {

    public Rabbit(int food, int number, int kindness) {
        super(food, number, kindness);
    }

    @Override
    public String toString() {
        return "Rabbit " + super.getFood() + " " + super.getNumber() + " " + super.getKindness();
    }
}
