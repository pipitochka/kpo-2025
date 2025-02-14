package zoo.domains.things;

import lombok.ToString;

/**
 * class of computer.
 */
public class Computer extends Thing {

    public Computer(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return "Computer " + super.getNumber();
    }
}
