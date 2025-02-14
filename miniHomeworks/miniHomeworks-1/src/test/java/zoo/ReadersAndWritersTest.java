package zoo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zoo.domains.Zoo;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.animals.Tiger;
import zoo.domains.animals.Wolf;
import zoo.domains.files.MyFileReader;
import zoo.domains.files.MyFileWriter;
import zoo.domains.things.Computer;
import zoo.domains.things.Table;

/**
 * class to test reader and wrtiter.
 */
public class ReadersAndWritersTest {
    @Test
    @DisplayName("read and write test")
    void readAndWriteTest() {
        Zoo zoo = new Zoo();
        Monkey monkey = new Monkey(10, 1, 7);
        Rabbit rabbit = new Rabbit(20, 2, 5);
        Tiger tiger = new Tiger(30, 3);
        Wolf wolf = new Wolf(40, 4);
        Monkey monkey2 = new Monkey(10, 1, 3);
        Rabbit rabbit2 = new Rabbit(20, 2, 9);

        Computer computer = new Computer(1);
        Table table = new Table(2);

        zoo.addAnimalWithoutcLinic(monkey);
        zoo.addAnimalWithoutcLinic(rabbit);
        zoo.addAnimalWithoutcLinic(tiger);
        zoo.addAnimalWithoutcLinic(wolf);
        zoo.addAnimalWithoutcLinic(monkey2);
        zoo.addAnimalWithoutcLinic(rabbit2);

        zoo.addThing(computer);
        zoo.addThing(table);

        MyFileReader reader = new MyFileReader();
        MyFileWriter writer = new MyFileWriter();

        writer.writeFile("data.txt", zoo);

        Zoo zooOut = reader.readFromFile("data.txt");

        assertThat(zooOut.getNumberOfAnimals()).isEqualTo(6);
        assertThat(zooOut.getNumberOfThings()).isEqualTo(2);
    }
}
