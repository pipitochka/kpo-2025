package zoo.domains.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import zoo.domains.valueobjects.enums.AnimalTypes;

/**
 * class of enclosure.
 */
@ToString
@Slf4j
@RequiredArgsConstructor
public class Enclosure {

    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final AnimalTypes animalType;
    @Getter
    private final int maxSize;
    @Getter
    private int currentSize = 0;

    private List<Animal> animals = new ArrayList<Animal>();

    /**
     * function to add animal to enclosure.
     *
     * @param animal which will be added
     * @throws Exception if not enough place or not tipe
     */
    public void add(Animal animal) throws Exception {
        if (animals.size() < maxSize && animal.getType() == animalType) {
            animals.add(animal);
            animal.moveToEnclosure(this);
            currentSize++;
            log.info("Added animal {} to enclosure {}", animal.getAnimalId(), id);
        } else {
            throw new Exception("To much animals in Enclosure");
        }
    }

    /**
     * function to remove animal from enclosure.
     *
     * @param animal which will be moved.
     */
    public void remove(Animal animal) {
        animals.remove(animal);
        currentSize--;
        animal.moveFromEnclosure();
        log.info("Removed animal {} from enclosure {}", animal.getAnimalId(), id);
    }

    public void clean() {
        log.info("Cleaning up enclosure{}", id);
    }
}
