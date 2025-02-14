package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.animals.Tiger;
import zoo.domains.animals.Wolf;

/**
 * class to test animal.
 */
public class AnimalTest {

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        Monkey monkey = new Monkey(10, 1, 3);
        Rabbit rabbit = new Rabbit(20, 2, 3);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);
    }

    @Test
    @DisplayName("Test getter and setter field")
    void testGetterAndSetter() {
        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 9);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);

        assertThat(monkey.getFood()).isEqualTo(10);
        assertThat(rabbit.getFood()).isEqualTo(20);
        assertThat(tiger.getFood()).isEqualTo(30);
        assertThat(wolf.getFood()).isEqualTo(40);

        assertThat(monkey.getNumber()).isEqualTo(1);
        assertThat(rabbit.getNumber()).isEqualTo(2);
        assertThat(tiger.getNumber()).isEqualTo(3);
        assertThat(wolf.getNumber()).isEqualTo(4);

        assertThat(monkey.getKindness()).isEqualTo(7);
        assertThat(rabbit.getKindness()).isEqualTo(9);

        monkey.setFood(100);
        monkey.setKindness(4);
        monkey.setNumber(10);

        rabbit.setFood(200);
        rabbit.setKindness(3);
        rabbit.setNumber(20);

        tiger.setFood(300);
        tiger.setNumber(30);

        wolf.setFood(400);
        wolf.setNumber(40);

        assertThat(monkey.getFood()).isEqualTo(100);
        assertThat(rabbit.getFood()).isEqualTo(200);
        assertThat(tiger.getFood()).isEqualTo(300);
        assertThat(wolf.getFood()).isEqualTo(400);

        assertThat(monkey.getNumber()).isEqualTo(10);
        assertThat(rabbit.getNumber()).isEqualTo(20);
        assertThat(tiger.getNumber()).isEqualTo(30);
        assertThat(wolf.getNumber()).isEqualTo(40);

        assertThat(monkey.getKindness()).isEqualTo(4);
        assertThat(rabbit.getKindness()).isEqualTo(3);
    }
}
