package zoo.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zoo.domains.animals.Animal;
import zoo.domains.animals.Herbo;
import zoo.domains.things.Thing;
import zoo.interfaces.InterfaceVetClinic;

/**
 * main class of zoo.
 */
@Component
@ToString
public class Zoo {

    @Getter
    @Setter
    @Autowired
    InterfaceVetClinic vetClinic;


    @Getter
    private List<Animal> animals;

    @Getter
    private List<Thing> things;

    /**
     * function which add animal in zoo if it helath.
     *
     * @param animal which we want to add.
     */
    public void addAnimal(Animal animal) {
        if (vetClinic != null && vetClinic.isHealthy(animal)) {
            this.animals.add(animal);
        }
    }

    public void addAnimalWithoutcLinic(Animal animal) {
        this.animals.add(animal);
    }

    public void addThing(Thing thing) {
        this.things.add(thing);
    }

    public int calculateFood() {
        return this.animals.stream().mapToInt(Animal::getFood).sum();
    }

    /**
     * chaeck which animals can be added to contact zoo.
     *
     * @return list of good animals.
     */
    public List<Animal> getAnimalsToContactZoo() {
        return this.animals.stream()
                .filter(animal -> animal instanceof Herbo)
                .filter(animal -> ((Herbo) animal).getKindness() > 5)
                .collect(Collectors.toList());
    }

    public Zoo() {
        this.animals = new ArrayList<>();
        this.things = new ArrayList<>();
    }

    public int getNumberOfAnimals() {
        return this.animals.size();
    }

    public int getNumberOfThings() {
        return this.things.size();
    }
}
