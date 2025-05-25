package zoo.domains.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;
import zoo.domains.Zoo;
import zoo.domains.animals.Animal;
import zoo.domains.things.Thing;
import zoo.interfaces.InterfaceFileWriter;

/**
 * realizations of InterfaceFileWriter which write information if file.
 */
@Component
public class MyFileWriter implements InterfaceFileWriter {

    /** function which write information if file.
     *
     * @param fileName address of file
     * @param zoo zoo whose information we will write.
     */
    @Override
    public void writeFile(String fileName, Zoo zoo) {
        List<Animal> animals = zoo.getAnimals();
        List<Thing> things = zoo.getThings();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Animal animal : animals) {
                writer.write(animal.toString());
                writer.write("\n");
            }
            for (Thing thing : things) {
                writer.write(thing.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
