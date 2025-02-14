package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.domains.MyVetClinic;
import zoo.domains.Zoo;
import zoo.domains.animals.Animal;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.animals.Tiger;
import zoo.domains.animals.Wolf;
import zoo.domains.things.Computer;
import zoo.domains.things.Table;
import zoo.domains.things.Thing;

/**
 * class to test zoo.
 */
public class ZooTest {
    @Test
    @DisplayName("Add animals test")
    public void testAddAnimals() {
        Zoo zoo = new Zoo();
        MyVetClinic myVetClinic = new MyVetClinic();
        zoo.setVetClinic(myVetClinic);

        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 9);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);

        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);
        zoo.addAnimal(wolf);

        List<Animal> animals = zoo.getAnimals();

        assertThat(animals.size()).isEqualTo(4);
        assertThat(animals.get(0)).isEqualTo(monkey);
        assertThat(animals.get(1)).isEqualTo(rabbit);
        assertThat(animals.get(2)).isEqualTo(tiger);
        assertThat(animals.get(3)).isEqualTo(wolf);
    }

    @Test
    @DisplayName("Add thing test")
    public void testAddThings() {
        Zoo zoo = new Zoo();

        Computer computer = new Computer(1);
        Table table = new Table(2);

        zoo.addThing(computer);
        zoo.addThing(table);

        List<Thing> things = zoo.getThings();

        assertThat(things.size()).isEqualTo(2);
        assertThat(things.get(0)).isEqualTo(computer);
        assertThat(things.get(1)).isEqualTo(table);
    }

    @Test
    @DisplayName("Test calculate food")
    public void testCalculateFood() {
        Zoo zoo = new Zoo();
        MyVetClinic myVetClinic = new MyVetClinic();
        zoo.setVetClinic(myVetClinic);

        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 9);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);

        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);
        zoo.addAnimal(wolf);

        assertThat(zoo.calculateFood()).isEqualTo(100);
    }

    @Test
    @DisplayName("Test animals to contact zoo")
    public void testAnimalsToContactZoo() {
        Zoo zoo = new Zoo();
        MyVetClinic myVetClinic = new MyVetClinic();
        zoo.setVetClinic(myVetClinic);

        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 5);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);
        Monkey monkey2 = new Monkey(10, 1, 3);
        Rabbit rabbit2 = new Rabbit(20, 2, 9);

        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);
        zoo.addAnimal(wolf);
        zoo.addAnimal(monkey2);
        zoo.addAnimal(rabbit2);

    }

    @Test
    @DisplayName("Test all")
    public void testAll() {
        Zoo zoo = new Zoo();
        MyVetClinic myVetClinic = new MyVetClinic();
        zoo.setVetClinic(myVetClinic);

        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 5);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);
        Monkey monkey2 = new Monkey(10, 1, 3);
        Rabbit rabbit2 = new Rabbit(20, 2, 9);

        Computer computer = new Computer(1);
        Table table = new Table(2);

        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);
        zoo.addAnimal(wolf);
        zoo.addAnimal(monkey2);
        zoo.addAnimal(rabbit2);

        zoo.addThing(computer);
        zoo.addThing(table);

        List<Animal> animals = zoo.getAnimals();
        List<Thing> things = zoo.getThings();
        List<Animal> animalsToContactZoo = zoo.getAnimalsToContactZoo();

        assertThat(animalsToContactZoo.size()).isEqualTo(2);
        assertThat(animalsToContactZoo.get(0)).isEqualTo(monkey);
        assertThat(animalsToContactZoo.get(1)).isEqualTo(rabbit2);

        assertThat(animals.size()).isEqualTo(6);
        assertThat(animals.get(0)).isEqualTo(monkey);
        assertThat(animals.get(1)).isEqualTo(rabbit);
        assertThat(animals.get(2)).isEqualTo(tiger);
        assertThat(animals.get(3)).isEqualTo(wolf);
        assertThat(animals.get(4)).isEqualTo(monkey2);
        assertThat(animals.get(5)).isEqualTo(rabbit2);

        assertThat(things.size()).isEqualTo(2);
        assertThat(things.get(0)).isEqualTo(computer);
        assertThat(things.get(1)).isEqualTo(table);

        assertThat(zoo.getNumberOfAnimals()).isEqualTo(6);
        assertThat(zoo.getNumberOfThings()).isEqualTo(2);
    }
}

