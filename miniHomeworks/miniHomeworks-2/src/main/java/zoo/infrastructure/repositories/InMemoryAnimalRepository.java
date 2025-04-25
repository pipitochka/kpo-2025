package zoo.infrastructure.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zoo.application.interfaces.AnimalRepository;
import zoo.application.interfaces.EnclosureRepository;
import zoo.domains.entities.Animal;
import zoo.domains.entities.Enclosure;


/**
 * realization of AnimalRepository in memory.
 */
@Component
@RequiredArgsConstructor
public class InMemoryAnimalRepository implements AnimalRepository {
    private final Map<UUID, Animal> animals = new HashMap<>();

    private final EnclosureRepository enclosureRepository;

    @Override
    public void add(Animal animal) {
        animals.put(animal.getAnimalId(), animal);
    }

    @Override
    public void remove(Animal animal) {
        if (animal.getEnclosure() != null) {
            Enclosure enclosure = animal.getEnclosure();
            enclosure.remove(animal);
        }
        animals.remove(animal.getAnimalId());
    }

    @Override
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals.values());
    }

    @Override
    public Optional<Animal> getAnimalById(UUID id) {
        return Optional.ofNullable(animals.get(id));
    }

    @Override
    public Optional<Animal> getAnimalByName(String name) {
        return animals.values().stream()  // Ищем животное по имени
                .filter(animal -> animal.getName().equals(name))
                .findFirst();
    }

    @Override
    public void clear() {
        animals.clear();
    }
}
