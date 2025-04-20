package zoo.domains.entities;

import lombok.RequiredArgsConstructor;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
public class Enclosure {
    private final AnimalTypes animalType;
    private final int maxSize;
    private int currentSize;

    private List<Animal> animals = new ArrayList<Animal>();

    public void add(Animal animal) throws Exception {
        if (animals.size() < maxSize) {
            animals.add(animal);
            animal.MoveToEnclosure(this);
            currentSize++;
        }
        else{
            throw new Exception("To much animals in Enclosure");
        }
    }

    public void remove(Animal animal) {
        animals.remove(animal);
        currentSize--;
        animal.MoveFromEnclosure();
    }

    public void clean(){}
}
