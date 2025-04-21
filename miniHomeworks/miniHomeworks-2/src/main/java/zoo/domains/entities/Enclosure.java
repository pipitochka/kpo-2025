package zoo.domains.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueObjects.enums.AnimalTypes;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@ToString
@Slf4j
@RequiredArgsConstructor
public class Enclosure {
    @Getter
    private final UUID id = UUID.randomUUID();

    private final AnimalTypes animalType;
    private final int maxSize;
    private int currentSize = 0;

    private List<Animal> animals = new ArrayList<Animal>();

    public void add(Animal animal) throws Exception {
        if (animals.size() < maxSize) {
            animals.add(animal);
            animal.MoveToEnclosure(this);
            currentSize++;
            log.info("Added animal {} to enclosure {}", animal.getAnimalId(), id);
        }
        else{
            throw new Exception("To much animals in Enclosure");
        }
    }

    public void remove(Animal animal) {
        animals.remove(animal);
        currentSize--;
        animal.MoveFromEnclosure();
        log.info("Removed animal {} from enclosure {}", animal.getAnimalId(), id);
    }

    public void clean(){
        log.info("Cleaning up enclosure{}", id);
    }
}
