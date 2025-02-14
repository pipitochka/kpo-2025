package zoo.domains.things;

import lombok.ToString;
import zoo.interfaces.InterfaceInventory;

/**
 * abstract class of things.
 */
@ToString
public abstract class Thing implements InterfaceInventory {

    private int number;

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

    Thing(int number) {
        this.number = number;
    }
}
