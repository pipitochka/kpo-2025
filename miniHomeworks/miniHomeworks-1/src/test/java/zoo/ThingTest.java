package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.domains.things.Computer;
import zoo.domains.things.Table;

/**
 * class to test fings.
 */
public class ThingTest {
    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        Computer computer = new Computer(1);
        Table table = new Table(2);
    }

    @Test
    @DisplayName("Test getter and setter field")
    void testGetterAndSetter() {
        Computer computer = new Computer(1);
        Table table = new Table(2);

        assertThat(computer.getNumber()).isEqualTo(1);
        assertThat(table.getNumber()).isEqualTo(2);

        computer.setNumber(3);
        table.setNumber(4);

        assertThat(computer.getNumber()).isEqualTo(3);
        assertThat(table.getNumber()).isEqualTo(4);
    }
}
