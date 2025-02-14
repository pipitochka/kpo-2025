package zoo.domains.things;

import lombok.ToString;

/**
 * class of table.
 */
public class Table extends Thing {

    public Table(int number) {
        super(number);
    }

    @Override
    public String toString() {
        return "Table " + super.getNumber();
    }
}
