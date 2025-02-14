package zoo.domains.animals;

import lombok.ToString;
import zoo.interfaces.InterfaceAlive;
import zoo.interfaces.InterfaceInventory;

/**
 * class animals.
 */
@ToString
public abstract class Animal implements InterfaceAlive, InterfaceInventory {

    private int food;
    private int number;

    public Animal(int food, int number) {
        this.food = food;
        this.number = number;
    }

    /**
     * function which st daily food.
     *
     * @param food daily food.
     */
    @Override
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * funrtion which return daily food.
     *
     * @return daily food.
     */
    @Override
    public int getFood() {
        return food;
    }

    /**
     * fucntion which set number.
     *
     * @param number new number.
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * function which get number.
     *
     * @return number
     */
    @Override
    public int getNumber() {
        return number;
    }
}
