package zoo.domains.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Component;
import zoo.domains.Zoo;
import zoo.domains.animals.Monkey;
import zoo.domains.animals.Rabbit;
import zoo.domains.animals.Tiger;
import zoo.domains.animals.Wolf;
import zoo.domains.things.Computer;
import zoo.domains.things.Table;
import zoo.interfaces.InterfaceFileReader;

/**
 * realisation of InterfaceFileReader which read from file and make a zoo.
 */
@Component
public class MyFileReader implements InterfaceFileReader {

    /** function  to read information from file.
     *
     * @param fileName name of file
     * @return zoo
     */
    @Override
    public Zoo readFromFile(String fileName) {
        Zoo zoo = new Zoo();
        int food;
        int number;
        int happiness;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                switch (split[0]) {
                    case "Monkey":
                        if (split.length != 4) {
                            throw new IllegalArgumentException("Monkey does not have 4 parameters");
                        }
                        food = Integer.valueOf(split[1]);
                        number = Integer.valueOf(split[2]);
                        happiness = Integer.valueOf(split[3]);
                        Monkey monkey = new Monkey(food, number, happiness);
                        zoo.addAnimalWithoutcLinic(monkey);
                        break;
                    case "Rabbit":
                        if (split.length != 4) {
                            throw new IllegalArgumentException("Rabbit does not have 4 parameters");
                        }
                        food = Integer.valueOf(split[1]);
                        number = Integer.valueOf(split[2]);
                        happiness = Integer.valueOf(split[3]);
                        Rabbit rabbit = new Rabbit(food, number, happiness);
                        zoo.addAnimalWithoutcLinic(rabbit);
                        break;
                    case "Tiger":
                        if (split.length != 3) {
                            throw new IllegalArgumentException("Tiger does not have 3 parameters");
                        }
                        food = Integer.valueOf(split[1]);
                        number = Integer.valueOf(split[2]);
                        Tiger tiger = new Tiger(food, number);
                        zoo.addAnimalWithoutcLinic(tiger);
                        break;
                    case "Wolf":
                        if (split.length != 3) {
                            throw new IllegalArgumentException("Wolf does not have 3 parameters");
                        }
                        food = Integer.valueOf(split[1]);
                        number = Integer.valueOf(split[2]);
                        Wolf wolf = new Wolf(food, number);
                        zoo.addAnimalWithoutcLinic(wolf);
                        break;
                    case "Computer":
                        if (split.length != 2) {
                            throw new IllegalArgumentException("Computer does not have 2 parameters");
                        }
                        number = Integer.valueOf(split[1]);
                        Computer computer = new Computer(number);
                        zoo.addThing(computer);
                        break;
                    case "Table":
                        if (split.length != 2) {
                            throw new IllegalArgumentException("Table does not have 2 parameters");
                        }
                        number = Integer.valueOf(split[1]);
                        Table table = new Table(number);
                        zoo.addThing(table);
                        break;
                    default:
                        throw new IllegalArgumentException("error in file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return zoo;
    }
}
